package ru.volnenko.se.listener.data.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
public class DataXmlSaveCommand extends AbstractEventListener {

    @Autowired
    private IDomainService domainService;
    
    @Autowired
    private Scanner scanner;
    
    @Override
    public String command() {
        return "data-xml-save";
    }

    @Override
    public String description() {
        return "Save Domain to XML.";
    }
    
    @Override
    @EventListener(condition = "#event.command == 'data-xml-save'")
    public void execute(CommandEvent event) throws Exception {
        System.out.println("[DATA XML SAVE]");
        System.out.println("Please enter domain name to save:");
        String domainName = scanner.nextLine();
        Domain domain = domainService.findByName(domainName);
        if (domain == null) {
            System.out.println("Domain not found!");
            return;
        }
        writeDomain(domain);
        System.out.println("[OK]");
    }
    
    private void writeDomain(Domain domain) throws Exception {
        final ObjectMapper objectMapper = new XmlMapper();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        final String json = objectWriter.writeValueAsString(domain);
        final byte[] data = json.getBytes("UTF-8");
        final File file = new File(DataConstant.FILE_XML);
        Files.write(file.toPath(), data);
    }
}
