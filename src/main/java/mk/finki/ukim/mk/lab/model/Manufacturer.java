package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Manufacturer {

    private Long id;
    private String name;
    private Country country;
    private String address;

    public Manufacturer(String name, Country country, String address) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.country = country;
        this.address = address;
    }
}
