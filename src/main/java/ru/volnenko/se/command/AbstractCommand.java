package ru.volnenko.se.command;

import org.springframework.beans.factory.annotation.Autowired;
import ru.volnenko.se.controller.Bootstrap;

/**
 * @author Denis Volnenko
 */
public abstract class AbstractCommand {

    @Autowired
    protected Bootstrap bootstrap;

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract void execute() throws Exception;

    public abstract String command();

    public abstract String description();

}
