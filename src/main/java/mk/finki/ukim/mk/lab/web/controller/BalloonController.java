package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.OptionalInt;


@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error ,Model model,HttpServletRequest request){
        if(error!=null&&!error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        String nameAndDesc = (String) request.getSession().getAttribute("nameAndDesc");
        if(nameAndDesc!=null&&!nameAndDesc.isEmpty()){
            model.addAttribute("balloons",balloonService.findByNameOrDescription(nameAndDesc));
        }
        else {
            model.addAttribute("balloons", balloonService.listAll());
        }
        model.addAttribute("bodyContent","listBalloons");
        return "master-template";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        balloonService.deleteById(id);
        return "redirect:/balloons";
    }


    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage(Model model){
        model.addAttribute("balloons",balloonService.listAll());
        model.addAttribute("manufacturers",manufacturerService.findAll());
        model.addAttribute("bodyContent","add-balloon");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id,Model model){

        if(balloonService.findBalloonById(id).isPresent()){
            model.addAttribute("balloon",balloonService.findBalloonById(id).get());
            model.addAttribute("manufacturers",manufacturerService.findAll());
            model.addAttribute("bodyContent","add-balloon");
            return "master-template";
        }

        return "redirect:/balloons?error=BalloonNotFound";
    }



    @PostMapping
    public void sendBalloonColor(HttpServletRequest request, HttpServletResponse response, Model model){
        String color = request.getParameter("color");
        request.getSession().setAttribute("color",color);
        try {
            response.sendRedirect("/selectBalloon");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/addBalloon")
    public String saveBalloon(@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam String desc,
                              @RequestParam Long manufacturer){
        if(id!=null){
            balloonService.editBalloon(id,name,desc,manufacturer);
        }
        else {
            balloonService.saveBalloon(name, desc, manufacturer);
        }
        return "redirect:/balloons";
    }

    @PostMapping("/nameAndDesc")
    public String getCountryName(@RequestParam String nameAndDesc,HttpServletRequest request){
        request.getSession().setAttribute("nameAndDesc",nameAndDesc);
        return "redirect:/balloons";
    }
}
