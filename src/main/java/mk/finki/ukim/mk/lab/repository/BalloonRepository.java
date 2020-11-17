package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

    private List<Balloon> balloons;

    @PostConstruct
    public void init(){
        balloons = new ArrayList<>();
        balloons.add(new Balloon("name1","desc1",new Manufacturer("name1",new Country(1L,"country1"),"add1")));
        balloons.add(new Balloon("name2","desc2",new Manufacturer("name2",new Country(2L,"country2"),"add2")));
        balloons.add(new Balloon("name3","desc3",new Manufacturer("name3",new Country(3L,"country3"),"add3")));
        balloons.add(new Balloon("name4","desc4",new Manufacturer("name4",new Country(4L,"country4"),"add4")));
        balloons.add(new Balloon("name5","desc5",new Manufacturer("name5",new Country(5L,"country5"),"add5")));
        balloons.add(new Balloon("name6","desc6",new Manufacturer("name6",new Country(6L,"country6"),"add6")));
        balloons.add(new Balloon("name7","desc7",new Manufacturer("name7",new Country(7L,"country7"),"add7")));
        balloons.add(new Balloon("name8","desc8",new Manufacturer("name8",new Country(8L,"country8"),"add8")));
        balloons.add(new Balloon("name9","desc9",new Manufacturer("name9",new Country(9L,"country9"),"add9")));
        balloons.add(new Balloon("name10","desc10",new Manufacturer("name10",new Country(10L,"country10"),"add10")));
    }

    public List<Balloon> findAllBalloons(){
        return balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return balloons.stream().filter(b->b.getColor().contains(text)||b.getSize().contains(text)).collect(Collectors.toList());
    }

    public Optional<Balloon> findBalloonById(Long id){
        return balloons.stream().filter(b->b.getId().equals(id)).findFirst();
    }

    public Optional<Balloon> save(String name,String description,Manufacturer manufacturer){
        balloons.removeIf(b->b.getName().equals(name)||b.getDescription().equals(description));
        Balloon balloon = new Balloon(name,description,manufacturer);
        balloons.add(balloon);
        return Optional.of(balloon);
    }

    public void deleteById(Long id){
        balloons.removeIf(b->b.getId().equals(id));
    }


    public List<Balloon> findBalloonByCountryName(String country){
        return balloons.stream().filter(b->b.getManufacturer().getCountry().getName().equals(country)).collect(Collectors.toList());
    }



}
