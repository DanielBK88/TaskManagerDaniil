package ru.volnenko.se.command.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.repository.ProjectRepository;
import ru.volnenko.se.service.ProjectService;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectClearCommand extends AbstractCommand {

    private ProjectService projectService;
    
    @Override
    public String command() {
        return "project-clear";
    }

    @Override
    public String description() {
        return "Remove all projects.";
    }

    @Override
    public void execute() {
        projectService.clear();
        System.out.println("[ALL PROJECTS REMOVED]");
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

}
