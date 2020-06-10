package ru.volnenko.se.listener.project;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IScannerService;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.service.ProjectService;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectCreateEventListener extends AbstractEventListener {

    private ProjectService projectService;
    
    private IScannerService scannerService;
    
    @Override
    public String description() {
        return "Create new project.";
    }

    @Override
    public String command() {
        return "project-create";
    }

    @Override
    @EventListener(condition = "#event.command == 'project-create'")
    public void execute(CommandEvent event) {
        scannerService.readInputAndDoTask("[PROJECT CREATE]\nENTER NAME:", name -> {
            projectService.createProject(name);
            System.out.println("Project " + name + " created successfully");
            System.out.println();
        });
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setScannerService(IScannerService scannerService) {
        this.scannerService = scannerService;
    }

}
