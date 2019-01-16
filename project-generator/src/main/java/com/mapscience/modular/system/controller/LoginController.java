package com.mapscience.modular.system.controller;

import com.mapscience.modular.system.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    //默认路径
    private final String PREFIX = "/modular/";


    @GetMapping(value = "/")
    public String index(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "/login";
        }
        return PREFIX + "index";
    }

    @GetMapping(value = "login")
    public String login(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        } else {
            return "/login";
        }
    }

    @PostMapping(value = "login")
    public String login(User user, HttpServletRequest request) {
        if (user != null) {
            if (user.getAccount() != null && !"".equals(user.getAccount())) {
                user.setAccount(user.getAccount().trim());
            }
            if (user.getPassword() != null && !"".equals(user.getPassword())) {
                user.setPassword(user.getPassword().trim());
            }
        }
        if ("admin".equals(user.getAccount()) && "admin".equals(user.getPassword())) {
            request.getSession().setAttribute("user", user);
        }
        return "redirect:/";

    }
}
