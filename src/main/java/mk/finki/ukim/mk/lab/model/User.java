package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="shop_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> carts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username){
        this.username = username;
    }

    public User() {

    }
}
