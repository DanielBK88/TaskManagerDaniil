package ru.volnenko.se.listener.data.bin;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IDomainService;
import ru.volnenko.se.entity.Domain;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.constant.DataConstant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataBinarySaveCommand extends AbstractEventListener {

    @Autowired
    private IDomainService domainService;

    @Autowired
    private Scanner scanner;
    
    @Override
    public String command() {
        return "data-bin-save";
    }

    @Override
    public String description() {
        return "Load data from binary file.";
    }

    @Override
    @EventListener(condition = "#event.command == 'data-bin-save'")
    public void execute(CommandEvent event) throws Exception {
        System.out.println("[DATA BINARY SAVE]");

        System.out.println("Please enter domain name to save:");
        String domainName = scanner.nextLine();
        Domain domain = domainService.findByName(domainName);
        if (domain == null) {
            System.out.println("Domain not found!");
            return;
        }

        final File file = new File(DataConstant.FILE_BINARY);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());

        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(domain);
        objectOutputStream.close();
        fileOutputStream.close();

        System.out.println("[OK]");
        System.out.println();

    }
}
