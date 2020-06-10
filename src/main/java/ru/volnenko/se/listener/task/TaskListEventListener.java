package ru.volnenko.se.listener.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.entity.Task;
import ru.volnenko.se.service.TaskService;

/**
 * @author Denis Volnenko
 */
public final class TaskListEventListener extends AbstractEventListener {
    
    private TaskService taskService;

    @Override
    public String command() {
        return "task-list";
    }

    @Override
    public String description() {
        return "Show all tasks.";
    }

    @Override
    @EventListener(condition = "#event.command == 'task-list'")
    public void execute(CommandEvent event) {
        System.out.println("[TASK LIST]");
        int index = 1;
        for (Task task: taskService.getListTask()) {
            System.out.println(index + ". " + task.getName());
            index++;
        }
        System.out.println();
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

}
