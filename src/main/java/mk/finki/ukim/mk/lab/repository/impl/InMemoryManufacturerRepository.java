package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryManufacturerRepository {

    private List<Manufacturer> manufacturerList;

//    @PostConstruct
//    public void init(){
//        manufacturerList = new ArrayList<>();
//        manufacturerList.add(new Manufacturer("Balulu",new Country(1234L,"USA"),"NY NY"));
//        manufacturerList.add(new Manufacturer("Fluuu",new Country(12345L,"Canada"),"Toronto TNE"));
//        manufacturerList.add(new Manufacturer("Cassa",new Country(123456L,"France"),"Paris PA"));
//        manufacturerList.add(new Manufacturer("Big-Balloon",new Country(1234567L,"Germany"),"Berlin BE"));
//        manufacturerList.add(new Manufacturer("Bally",new Country(12345678L,"England"),"London LN"));
//    }


    public List<Manufacturer> findAll(){
        return manufacturerList;
    }

    public List<Manufacturer> findManufacturerByCountryName(String name){
    return manufacturerList.stream().filter(m->m.getCountry().getName().equals(name)).collect(Collectors.toList());
    }

    public List<Manufacturer> findManufacturerByCountryId(Long id){
        return manufacturerList.stream().filter(m->m.getCountry().getId().equals(id)).collect(Collectors.toList());
    }

}
