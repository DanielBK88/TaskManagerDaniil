package ru.volnenko.se.listener.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskClearEventListener extends AbstractEventListener {

    @Autowired
    private ITaskService taskService;
    
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

}
