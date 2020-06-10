package ru.volnenko.se.listener.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.service.ProjectService;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectClearEventListener extends AbstractEventListener {

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
    @EventListener(condition = "#event.command == 'project-clear'")
    public void execute(CommandEvent event) {
        projectService.clear();
        System.out.println("[ALL PROJECTS REMOVED]");
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

}
