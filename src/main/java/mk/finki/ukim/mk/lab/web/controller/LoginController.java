package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;


    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest req, Model model){
        User user = null;
        try{
            user = authService.login(req.getParameter("username"),req.getParameter("password"));
            req.getSession().setAttribute("user",user);
            return "redirect:/balloons";
        } catch (RuntimeException ex){
            model.addAttribute("hasError",true);
            model.addAttribute("error",ex.getMessage());
            return "login";
        }
    }

}
