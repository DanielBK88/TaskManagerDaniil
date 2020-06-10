package ru.volnenko.se.listener.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;

/**
 * @author Denis Volnenko
 */
@Component
public final class HelpEventListener extends AbstractEventListener {
    
    private List<AbstractEventListener> listeners;

    @Override
    public String command() {
        return "help";
    }

    @Override
    public String description() {
        return "Show all commands.";
    }

    @Override
    @EventListener(condition = "#event.command == 'help'")
    public void execute(CommandEvent event) {
        for (AbstractEventListener listener: listeners) {
            System.out.println(listener.command()+ ": " + listener.description());
        }
        System.out.println(Thread.currentThread().getName());
    }

    @Autowired
    public void setListeners(List<AbstractEventListener> listeners) {
        this.listeners = listeners;
        listeners.add(this);
    }

}
