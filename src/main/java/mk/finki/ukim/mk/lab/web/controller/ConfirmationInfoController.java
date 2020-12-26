package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ConfirmationInfo")
public class ConfirmationInfoController {

    private final BalloonService balloonService;

    public ConfirmationInfoController(BalloonService balloonService) {
        this.balloonService = balloonService;
    }

    @GetMapping
    public String ConfirmationInfoPage(HttpServletRequest req, HttpServletResponse resp, Model model){
        model.addAttribute("ipAddress",req.getRemoteAddr());
        model.addAttribute("clientAgent",req.getHeader("User-Agent"));
        model.addAttribute("bodyContent","confirmationInfo");
        return "master-template";
    }


}
