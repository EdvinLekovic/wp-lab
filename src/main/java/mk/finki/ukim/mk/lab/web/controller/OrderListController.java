package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderListController {

    private final OrderService orderService;

    public OrderListController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String orderListPage(Model model){
        model.addAttribute("listOrders",orderService.listOrders());
        return "userOrders";
    }


}
