package ru.volnenko.se.listener.project;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectRemoveEventListener extends AbstractEventListener {

    @Override
    public String command() {
        return "project-remove";
    }

    @Override
    public String description() {
        return "Remove selected project.";
    }

    @Override
    @EventListener(condition = "#event.command == 'project-remove'")
    public void execute(CommandEvent event) {

    }

}
