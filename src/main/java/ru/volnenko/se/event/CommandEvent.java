package ru.volnenko.se.event;

import org.springframework.context.ApplicationEvent;

/**
 * An event corresponding to a command being entered by the user
 **/
public class CommandEvent extends ApplicationEvent {
    
    /**
     * The entered command
     **/
    private final String command;
    
    public CommandEvent(Object source, String command) {
        super(source);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
