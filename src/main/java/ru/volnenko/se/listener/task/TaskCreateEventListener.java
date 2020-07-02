package ru.volnenko.se.listener.task;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.entity.Project;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskCreateEventListener extends AbstractEventListener {
    
    @Autowired
    private ITaskService taskService;
    
    @Autowired
    private IProjectService projectService;
    
    @Autowired
    private Scanner scanner;

    @Override
    public String command() {
        return "task-create";
    }

    @Override
    public String description() {
        return "Create new task.";
    }

    @Override
    @EventListener(condition = "#event.command == 'task-create'")
    public void execute(CommandEvent event) {
        System.out.println("[TASK CREATE]");
        System.out.println("ENTER TASK NAME:");
        String taskName = scanner.nextLine();
        System.out.println("ENTER PROJECT NAME:");
        String projectName = scanner.nextLine();
        Project project = projectService.getProjectById(projectName);
        if (project == null) {
            System.out.println("A project with this name does not exist!");
            return;
        }
        taskService.createTaskByProject(project.getName(), taskName);
        System.out.println("Task " + taskName + " created successfully");
        System.out.println();
    }

}
