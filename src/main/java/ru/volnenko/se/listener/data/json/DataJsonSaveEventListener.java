package ru.volnenko.se.listener.data.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IDomainService;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.entity.Domain;

import java.io.File;
import java.nio.file.Files;

/**
 * @author Denis Volnenko
 */
@Component
public class DataJsonSaveEventListener extends AbstractEventListener {

    @Autowired
    private IDomainService domainService;
    
    @Autowired
    private Scanner scanner;
    
    @Override
    public String command() {
        return "data-json-save";
    }

    @Override
    public String description() {
        return "Save Domain to JSON.";
    }

    @Override
    @EventListener(condition = "#event.command == 'data-json-save'")
    public void execute(CommandEvent event) throws Exception {
        System.out.println("[DATA JSON SAVE]");
        System.out.println("Please enter domain name to save:");
        String domainName = scanner.nextLine();
        Domain domain = domainService.findByName(domainName);
        if (domain == null) {
            System.out.println("Domain not found!");
            return;
        }
        final ObjectMapper objectMapper = new ObjectMapper();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        final String json = objectWriter.writeValueAsString(domain);
        final byte[] data = json.getBytes("UTF-8");
        final File file = new File(DataConstant.FILE_JSON);
        Files.write(file.toPath(), data);
        System.out.println("[OK]");
    }
    
}
