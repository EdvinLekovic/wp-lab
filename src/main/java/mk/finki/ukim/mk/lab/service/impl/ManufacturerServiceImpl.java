package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.jpa.CountryRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final CountryRepository countryRepository;
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, CountryRepository countryRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer findByCountryName(String country){
        return manufacturerRepository.findByCountry(countryRepository.findByName(country));
    }
}
