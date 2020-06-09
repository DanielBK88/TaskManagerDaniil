package ru.volnenko.se.command.project;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.service.ProjectService;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectCreateCommand extends AbstractCommand {

    private ProjectService projectService;
    
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
    public void execute() {
        System.out.println("[PROJECT CREATE]");
        System.out.println("ENTER NAME:");
        final String name = scanner.nextLine();
        projectService.createProject(name);
        System.out.println("[OK]");
        System.out.println();
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
