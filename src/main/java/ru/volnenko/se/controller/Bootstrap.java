package ru.volnenko.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;
import ru.volnenko.se.api.service.IScannerService;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.*;
import ru.volnenko.se.error.CommandAbsentException;
import ru.volnenko.se.error.CommandCorruptException;

import java.util.*;

/**
 * @author Denis Volnenko
 */
@Controller
public final class Bootstrap implements ApplicationEventPublisherAware {

    private final Map<String, AbstractEventListener> commands = new LinkedHashMap<>();

    private IScannerService scannerService;
    
    private ApplicationEventPublisher publisher;

    public void registry(final AbstractEventListener command) {
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
        if (!AbstractEventListener.class.isAssignableFrom(clazz)) return;
        final Object command = clazz.newInstance();
        final AbstractEventListener abstractCommand = (AbstractEventListener) command;
        registry(abstractCommand);
    }

    public void init(final Class... classes) throws Exception {
        if (classes == null || classes.length == 0) throw new CommandAbsentException();
        registry(classes);
        start();
    }
    
    public void init(final Collection<AbstractEventListener> commands) throws Exception {
        if (commands == null || commands.isEmpty()) throw new CommandAbsentException();
        commands.forEach(this::registry);
        start();
    }

    private void start() throws Exception {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        String command = "";
        while (!"exit".equals(command)) {
            command = scannerService.readInputAndDoTask("Enter command: ", c -> {
                publisher.publishEvent(new CommandEvent(this, c));
            });
            Thread.sleep(500L); // Give command-threads a chance to take over the scanner ...
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Autowired
    public void setScannerService(IScannerService scannerService) {
        this.scannerService = scannerService;
    }

}
