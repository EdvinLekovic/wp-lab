package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime dateCreated;

    @OneToMany
    private List<Order> orders;

    public ShoppingCart(Long id, User user, LocalDateTime dateCreated, List<Order> orders) {
        this.id = id;
        this.user = user;
        this.dateCreated = dateCreated;
        this.orders = orders;
    }

    public ShoppingCart() {

    }
}
