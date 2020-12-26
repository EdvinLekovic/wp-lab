package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/BalloonOrder")
public class BalloonOrderController {

    private final BalloonService balloonService;
    private final OrderService orderService;


    public BalloonOrderController(BalloonService balloonService, OrderService orderService) {
        this.balloonService = balloonService;
        this.orderService = orderService;
    }

    @GetMapping
    public String BalloonOrderPage(Model model){
        model.addAttribute("bodyContent","deliveryInfo");
        return "master-template";
    }


    @PostMapping
    public String DeliveryInfo(HttpServletRequest req, HttpServletResponse resp,Model model){
        Balloon balloon = (Balloon) req.getSession().getAttribute("balloon");
        String username = req.getParameter("clientName");
        String password = req.getParameter("clientAddress");
        User user = new User(username,password);
        req.getSession().setAttribute("order",new Order(balloon.getColor(),balloon.getSize(),user));
        orderService.placeOrder(balloon.getColor(), balloon.getSize(),user);
        return "redirect:/ConfirmationInfo";
    }
}
