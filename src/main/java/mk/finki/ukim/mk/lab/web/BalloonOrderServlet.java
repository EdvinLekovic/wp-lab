package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="ballooon-order-servlet",urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;
    private final OrderService orderService;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp,req.getServletContext());
        springTemplateEngine.process("deliveryInfo.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Balloon balloon = (Balloon) req.getSession().getAttribute("balloon");
        String username = req.getParameter("clientName");
        String password = req.getParameter("clientAddress");
        User user = new User(username,password);
        req.getSession().setAttribute("order",new Order(balloon.getColor(),balloon.getSize(),user));
        orderService.placeOrder(balloon.getColor(), balloon.getSize(),user);
        resp.sendRedirect("/ConfirmationInfo");
    }
}
