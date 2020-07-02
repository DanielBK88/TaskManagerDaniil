package ru.volnenko.se.listener.project;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IDomainService;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.entity.Domain;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectCreateEventListener extends AbstractEventListener {

    @Autowired
    private IProjectService projectService;
    
    @Autowired
    private IDomainService domainService;
    
    @Autowired
    private Scanner scanner;
    
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
        System.out.println("[PROJECT CREATE]");
        System.out.println("ENTER PROJECT NAME:");
        String projectName = scanner.nextLine();
        System.out.println("ENTER DOMAIN NAME (will be created, if not existing!):");
        String domainName = scanner.nextLine();
        Domain domain = domainService.findByName(domainName);
        if (domain == null) {
            domain = domainService.createDomain(domainName);
        }
        projectService.createProject(projectName, domainName);
        System.out.println("Project " + projectName + " created successfully for domain " + domainName);
        System.out.println();
    }

}
