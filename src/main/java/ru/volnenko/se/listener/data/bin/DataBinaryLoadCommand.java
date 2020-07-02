package ru.volnenko.se.listener.data.bin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IDomainService;
import ru.volnenko.se.entity.Domain;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.constant.DataConstant;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataBinaryLoadCommand extends AbstractEventListener {
    
    @Autowired
    private IDomainService domainService;

    @Override
    public String command() {
        return "data-bin-load";
    }

    @Override
    public String description() {
        return "Save data to binary file.";
    }

    @Override
    @EventListener(condition = "#event.command == 'data-bin-load'")
    public void execute(CommandEvent event) throws Exception {
        System.out.println("[DATA BINARY LOAD]");
        final FileInputStream fileInputStream = new FileInputStream(DataConstant.FILE_BINARY);
        final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Domain domain = (Domain) objectInputStream.readObject();
        domainService.merge(domain);
        objectInputStream.close();
        fileInputStream.close();
        System.out.println("[OK]");
    }

}
