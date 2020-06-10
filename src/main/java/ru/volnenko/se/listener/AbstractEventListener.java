package ru.volnenko.se.listener;

import ru.volnenko.se.event.CommandEvent;

/**
 * @author Denis Volnenko
 */
public abstract class AbstractEventListener {

    public abstract void execute(CommandEvent event) throws Exception;

    public abstract String command();

    public abstract String description();

}
