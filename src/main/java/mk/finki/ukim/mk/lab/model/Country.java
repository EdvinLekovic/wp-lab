package mk.finki.ukim.mk.lab.model;


import lombok.Data;

@Data
public class Country {
    private Long id;
    private String name;

    public Country(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
