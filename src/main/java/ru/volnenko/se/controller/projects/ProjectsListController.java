package ru.volnenko.se.controller.projects;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.entity.Project;

@Controller
@RequestMapping(value = "/projects", method = RequestMethod.POST)
public class ProjectsListController {
    
    @Autowired
    private IProjectService projectService;

    @GetMapping
    @Secured("ROLE_NORMAL_USER")
    public ModelAndView domains(HttpServletRequest request) {
        String domain = request.getParameter("domain");
        List<Project> projects = projectService.getListProject().stream()
                .filter(project -> project.getDomain().getName().equals(domain))
                .collect(Collectors.toList());
        request.getSession().setAttribute("selectedDomain", domain);
        request.getSession().setAttribute("projects", projects);
        return new ModelAndView("projects");
    }
    
}
