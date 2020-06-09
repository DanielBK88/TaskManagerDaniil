package ru.volnenko.se.command.data.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.entity.Domain;

import java.io.File;
import java.nio.file.Files;
import ru.volnenko.se.service.DomainService;

/**
 * @author Denis Volnenko
 */
@Component
public class DataJsonSaveCommand extends AbstractCommand {

    private DomainService domainService;
    
    @Override
    public String command() {
        return "data-json-save";
    }

    @Override
    public String description() {
        return "Save Domain to JSON.";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("[DATA JSON SAVE]");
        Domain domain = getDomain();
        domainService.export(domain);
        final ObjectMapper objectMapper = new ObjectMapper();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        final String json = objectWriter.writeValueAsString(domain);
        final byte[] data = json.getBytes("UTF-8");
        final File file = new File(DataConstant.FILE_JSON);
        Files.write(file.toPath(), data);
        System.out.println("[OK]");
    }

    @Lookup
    public Domain getDomain() {
        return null; // To be overridden by Spring
    }

    @Autowired
    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }
    
}
