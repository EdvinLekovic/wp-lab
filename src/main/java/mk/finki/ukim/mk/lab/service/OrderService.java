package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> placeOrder(String balloonColor, String balloonSize, User user);
    List<Order> listOrders();
}
