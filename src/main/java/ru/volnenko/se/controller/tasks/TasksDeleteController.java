package ru.volnenko.se.controller.tasks;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.volnenko.se.api.service.ITaskService;

@Controller
@RequestMapping(value = "/processTasksDelete", method = RequestMethod.POST)
public class TasksDeleteController {
    
    @Autowired
    private ITaskService taskService;

    @GetMapping
    @Secured("ROLE_NORMAL_USER")
    public String delete(HttpServletRequest request) {
        String projectName = (String) request.getSession().getAttribute("selectedProject");
        String task = request.getParameter("task");
        taskService.removeTaskById(task);
        return "redirect:/tasks?project="+projectName;
    }
    
}
