package mk.finki.ukim.mk.lab.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;

    private String size;

    @ManyToOne
    private Manufacturer manufacturer;

    private String name;

    private String description;

    public Balloon(String color, String size) {
        this.id = (long) (Math.random()*1000);
        this.color = color;
        this.size = size;
    }

    public Balloon(String name,String description,Manufacturer manufacturer){
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Balloon() {

    }
}
