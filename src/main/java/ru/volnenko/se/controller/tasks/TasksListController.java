package ru.volnenko.se.controller.tasks;

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
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.entity.Task;

@Controller
@RequestMapping(value = "/tasks", method = RequestMethod.POST)
public class TasksListController {
    
    @Autowired
    private ITaskService taskService;

    @GetMapping
    @Secured("ROLE_NORMAL_USER")
    public ModelAndView domains(HttpServletRequest request) {
        String project = request.getParameter("project");
        List<Task> tasks = taskService.getListTask().stream()
                .filter(task -> task.getProject().getName().equals(project))
                .collect(Collectors.toList());
        request.getSession().setAttribute("selectedProject", project);
        request.getSession().setAttribute("tasks", tasks);
        return new ModelAndView("tasks");
    }
    
}
