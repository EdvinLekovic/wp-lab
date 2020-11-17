package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private List<Order> orders;

    @PostConstruct
    public void init(){
        orders = new ArrayList<>();
        for(int i = 1;i<=10;i++){
            String color = "color"+i;
            String size =  "size"+i;
            String name = "name"+i;
            String client = "client"+i;
            orders.add(new Order(color,size,name,client));
        }
    }

    public List<Order> findAllOrders() {
        return orders;
    }

    public Order addOrder(Order o){
        orders.add(o);
        return o;
    }
}
