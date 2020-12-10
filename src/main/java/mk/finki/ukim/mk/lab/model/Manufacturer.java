package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Country country;

    private String address;

    public Manufacturer(String name, Country country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Manufacturer(Country country){
        this.country = country;
    }

    public Manufacturer() {

    }
}
