package ru.volnenko.se.command.task;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import ru.volnenko.se.command.AbstractCommand;

/**
 * @author Denis Volnenko
 */
public final class TaskRemoveCommand extends AbstractCommand {
    
    private Scanner scanner;

    @Override
    public String command() {
        return "task-remove";
    }

    @Override
    public String description() {
        return "Remove selected task.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVING TASK]");
        System.out.println("Enter task order index:");
        final Integer orderIndex = nextInteger();
        if (orderIndex == null) {
            System.out.println("Error! Incorrect order index...");
            System.out.println();
            return;
        }
        System.out.println("[OK]");
    }

    public Integer nextInteger() {
        final String value = scanner.nextLine();
        if (value == null || value.isEmpty()) return null;
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Autowired
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
