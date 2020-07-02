package ru.volnenko.se.listener.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.entity.Task;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskListEventListener extends AbstractEventListener {
    
    @Autowired
    private ITaskService taskService;

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
            System.out.println(index + ". " + task.getName() + " project: " + task.getProject().getName() 
                    + " domain: " + task.getProject().getDomain().getName());
            index++;
        }
        System.out.println();
    }

}
