package ru.volnenko.se.command.task;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.service.TaskService;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskCreateCommand extends AbstractCommand {
    
    private TaskService taskService;
    
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
    public void execute() {
        System.out.println("[TASK CREATE]");
        System.out.println("ENTER NAME:");
        final String name = scanner.nextLine();
        taskService.createTask(name);
        System.out.println("[OK]");
        System.out.println();
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
