package ru.volnenko.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.volnenko.se.command.*;
import ru.volnenko.se.error.CommandAbsentException;
import ru.volnenko.se.error.CommandCorruptException;

import java.util.*;

/**
 * @author Denis Volnenko
 */
@Controller
public final class Bootstrap {

    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    private Scanner scanner;

    public void registry(final AbstractCommand command) {
        final String cliCommand = command.command();
        final String cliDescription = command.description();
        if (cliCommand == null || cliCommand.isEmpty()) throw new CommandCorruptException();
        if (cliDescription == null || cliDescription.isEmpty()) throw new CommandCorruptException();
        commands.put(cliCommand, command);
    }

    public void registry(final Class... classes) throws InstantiationException, IllegalAccessException {
        for (Class clazz: classes) registry(clazz);
    }

    public void registry(final Class clazz) throws IllegalAccessException, InstantiationException {
        if (!AbstractCommand.class.isAssignableFrom(clazz)) return;
        final Object command = clazz.newInstance();
        final AbstractCommand abstractCommand = (AbstractCommand) command;
        registry(abstractCommand);
    }

    public void init(final Class... classes) throws Exception {
        if (classes == null || classes.length == 0) throw new CommandAbsentException();
        registry(classes);
        start();
    }
    
    public void init(final Collection<AbstractCommand> commands) throws Exception {
        if (commands == null || commands.isEmpty()) throw new CommandAbsentException();
        commands.forEach(this::registry);
        start();
    }

    private void start() throws Exception {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        String command = "";
        while (!"exit".equals(command)) {
            command = scanner.nextLine();
            execute(command);
        }
    }

    private void execute(final String command) throws Exception {
        if (command == null || command.isEmpty()) return;
        final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        abstractCommand.execute();
    }

    @Autowired
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
