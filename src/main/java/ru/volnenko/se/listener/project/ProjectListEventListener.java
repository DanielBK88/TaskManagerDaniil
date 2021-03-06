package ru.volnenko.se.listener.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.event.CommandEvent;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.entity.Project;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectListEventListener extends AbstractEventListener {
    
    @Autowired
    private IProjectService projectService;

    @Override
    public String command() {
        return "project-list";
    }

    @Override
    public String description() {
        return "Show all projects.";
    }

    @Override
    @EventListener(condition = "#event.command == 'project-list'")
    public void execute(CommandEvent event) {
        System.out.println("[PROJECT LIST]");
        int index = 1;
        for (Project project: projectService.getListProject()) {
            System.out.println(index++ + ". " + project.getName() + " domain: " + project.getDomain().getName());
        }
        System.out.println();
    }

}
