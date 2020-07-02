package ru.volnenko.se.listener.data.bin;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.constant.DataConstant;

import java.io.File;
import java.nio.file.Files;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataBinaryClearCommand extends AbstractEventListener {

    @Override
    public String command() {
        return "data-bin-clear";
    }

    @Override
    public String description() {
        return "Remove binary data.";
    }

    @Override
    @EventListener(condition = "#event.command == 'data-bin-clear'")
    public void execute(CommandEvent event) throws Exception {
        final File file = new File(DataConstant.FILE_BINARY);
        Files.deleteIfExists(file.toPath());
    }

}
