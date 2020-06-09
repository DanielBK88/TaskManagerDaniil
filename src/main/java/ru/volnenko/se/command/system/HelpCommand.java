package ru.volnenko.se.command.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;

/**
 * @author Denis Volnenko
 */
@Component
public final class HelpCommand extends AbstractCommand {
    
    private List<AbstractCommand> commands;

    @Override
    public String command() {
        return "help";
    }

    @Override
    public String description() {
        return "Show all commands.";
    }

    @Override
    public void execute() {
        for (AbstractCommand command: commands) {
            System.out.println(command.command()+ ": " + command.description());
        }
    }

    @Autowired
    public void setCommands(List<AbstractCommand> commands) {
        this.commands = commands;
        commands.add(this);
    }

}
