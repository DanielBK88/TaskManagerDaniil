package ru.volnenko.se.listener.data.json;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public final class DataJsonLoadEventListener extends AbstractEventListener {

    @Autowired
    private IDomainService domainService;
    
    @Override
    public String command() {
        return "data-json-load";
    }

    @Override
    public String description() {
        return "Load Domain from JSON.";
    }

    @Override
    @EventListener(condition = "#event.command == 'data-json-load'")
    public void execute(CommandEvent event) throws Exception {
        System.out.println("[LOAD JSON DATA]");
        final File file = new File(DataConstant.FILE_JSON);
        if (!exists(file)) return;
        final byte[] bytes = Files.readAllBytes(file.toPath());
        final String json = new String(bytes, "UTF-8");
        final ObjectMapper objectMapper = new ObjectMapper();
        final Domain domain = objectMapper.readValue(json, Domain.class);
        domainService.merge(domain);
        System.out.println("[OK]");
    }

    private boolean exists(final File file) {
        if (file == null) return false;
        final boolean check = file.exists();
        if (!check) System.out.println("FILE NOT FOUND");
        return check;
    }

}
