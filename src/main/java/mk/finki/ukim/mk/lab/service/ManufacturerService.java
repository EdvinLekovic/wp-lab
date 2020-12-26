package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> findAll();
    Manufacturer findByCountryName(String countryName);
    Optional<Manufacturer> save(String name, Country country, String address);
}
