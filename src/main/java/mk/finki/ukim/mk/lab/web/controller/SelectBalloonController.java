package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/selectBalloon")
public class SelectBalloonController {

    private final BalloonService balloonService;

    public SelectBalloonController(BalloonService balloonService) {
        this.balloonService = balloonService;
    }

    @GetMapping
    public String selectBalloonPage(Model model){
        model.addAttribute("bodyContent","selectBalloonSize.html");
        return "master-template";
    }

    @PostMapping
    public String sendingSelectedBalloon(HttpServletRequest req, HttpServletResponse resp){
        String color = (String) req.getSession().getAttribute("color");
        String size = req.getParameter("size");
        Balloon balloon = new Balloon(color,size);
        req.getSession().setAttribute("balloon",balloon);
        return "redirect:/BalloonOrder";
    }
}
