package ru.volnenko.se.controller.projects;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.volnenko.se.api.service.IProjectService;

@Controller
@RequestMapping(value = "/processProjectsCreate", method = RequestMethod.POST)
public class ProjectsCreateController {
    
    @Autowired
    private IProjectService projectService;

    @GetMapping
    @Secured("ROLE_NORMAL_USER")
    public String create(HttpServletRequest request) {
        String domainName = (String) request.getSession().getAttribute("selectedDomain");
        String projectName = request.getParameter("projectName");
        projectService.createProject(projectName, domainName);
        return "redirect:/projects?domain="+domainName;
    }
    
}
