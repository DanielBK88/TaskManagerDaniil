package ru.volnenko.se.listener.task;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IScannerService;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.service.TaskService;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskCreateEventListener extends AbstractEventListener {
    
    private TaskService taskService;
    
    private IScannerService scannerService;

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
        scannerService.readInputAndDoTask("[TASK CREATE]\nENTER NAME:", name -> {
            taskService.createTask(name);
            System.out.println("Task " + name + " created successfully");
            System.out.println();
        });
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setScannerService(IScannerService scannerService) {
        this.scannerService = scannerService;
    }

}
