package ru.volnenko.se.listener.project;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectRemoveEventListener extends AbstractEventListener {
    
    @Autowired
    private Scanner scanner;
    
    @Autowired
    private IProjectService projectService;

    @Override
    public String command() {
        return "project-remove";
    }

    @Override
    public String description() {
        return "Remove selected project.";
    }

    @Override
    @EventListener(condition = "#event.command == 'project-remove'")
    public void execute(CommandEvent event) {
        System.out.println("[PROJECT REMOVE]");
        System.out.println("ENTER PROJECT NAME:");
        String projectName = scanner.nextLine();
        
        projectService.removeProjectById(projectName);
        
        System.out.println("project deleted successfully.");
    }

}
