package ru.volnenko.se.service;

import java.util.Arrays;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.api.repository.ITaskRepository;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.entity.Project;
import ru.volnenko.se.entity.Task;

import java.util.List;

/**
 * @author Denis Volnenko
 */
@Service
@Transactional
public final class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;
    
    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public Task getTaskById(final String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Invalid task id!");
        }
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task merge(final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("The task to merge is null!");
        }
        return taskRepository.save(task);
    }

    @Override
    public void removeTaskById(final String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Invalid task id!");
        }
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return;
        }
        task.getProject().getTasks().remove(task);
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getListTask() {
        return taskRepository.findAll();
    }

    @Override
    public void clear() {
        taskRepository.deleteAll();
    }

    @Override
    public Task createTaskByProject(final String projectId, final String taskName) {
        if (StringUtils.isEmpty(taskName)) {
            throw new IllegalArgumentException("Invalid task name!");
        }
        final Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            throw new IllegalArgumentException("Could not find a project with id " + projectId + "!");
        }
        Task task = new Task();
        task.setName(taskName);
        task.setProject(project);
        taskRepository.save(task);
        return task;
    }

    @Override
    public void merge(Task... tasks) {
        if (tasks.length == 0) {
            return;
        }
        taskRepository.saveAll(Arrays.asList(tasks));
    }

}
