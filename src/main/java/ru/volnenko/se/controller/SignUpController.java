package ru.volnenko.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.volnenko.se.api.service.IUserService;

@Controller
@RequestMapping(value = "/perform_signup", method = RequestMethod.POST)
public class SignUpController {
    
    @Autowired
    private IUserService userService;

    @GetMapping
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String password_submit
            ) {
        if (!password.equals(password_submit)) {
            return "redirect:/signup?error=Passwords do not match!";
        } else if (userService.addUser(username, password, "NORMAL_USER") == null) {
            return "redirect:/signup?error=A user with this name does already exist!";
        } else {
            return "redirect:/start?signupok=true";
        }
    }
    
}
