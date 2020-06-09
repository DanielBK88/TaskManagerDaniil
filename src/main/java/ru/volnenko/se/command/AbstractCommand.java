package ru.volnenko.se.command;

/**
 * @author Denis Volnenko
 */
public abstract class AbstractCommand {

    public abstract void execute() throws Exception;

    public abstract String command();

    public abstract String description();

}
