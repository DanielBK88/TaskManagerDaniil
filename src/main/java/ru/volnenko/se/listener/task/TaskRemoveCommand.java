package ru.volnenko.se.listener.task;

import java.util.Scanner;
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
public final class TaskRemoveCommand extends AbstractEventListener {
    
    @Autowired
    private Scanner scanner;
    
    @Autowired
    private ITaskService taskService;

    @Override
    public String command() {
        return "task-remove";
    }

    @Override
    public String description() {
        return "Remove selected task.";
    }

    @Override
    @EventListener(condition = "#event.command == 'task-remove'")
    public void execute(CommandEvent event) {
        System.out.println("[REMOVING TASK]");
        System.out.println("Enter task name:");
        String taskName = scanner.nextLine();
        taskService.removeTaskById(taskName);
        System.out.println("[OK]");
    }
    

}
