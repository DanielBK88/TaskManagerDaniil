package ru.volnenko.se.listener.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.service.TaskService;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskClearEventListener extends AbstractEventListener {

    private TaskService taskService;
    
    @Override
    public String description() {
        return "Remove all tasks.";
    }

    @Override
    public String command() {
        return "task-clear";
    }

    @Override
    @EventListener(condition = "#event.command == 'task-clear'")
    public void execute(CommandEvent event) {
        taskService.clear();
        System.out.println("[ALL TASK REMOVED]");
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

}
