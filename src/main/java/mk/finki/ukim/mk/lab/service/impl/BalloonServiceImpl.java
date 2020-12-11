package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryBalloonRepository;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryManufacturerRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerService manufacturerService;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerService manufacturerService) {
        this.balloonRepository = balloonRepository;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByName(String name) {
        return balloonRepository.findAllByNameContains(name);
    }

    @Override
    public Optional<Balloon> findBalloonById(Long id) {
        return balloonRepository.findById(id);
    }

    public List<Balloon> findByNameOrDescription(String nameOrDesc){
        return Stream.concat(balloonRepository.findAllByNameContains(nameOrDesc).stream(),balloonRepository.findAllByDescriptionContains(nameOrDesc).stream()).collect(Collectors.toList());
    }

    @Override
    public Optional<Balloon> saveBalloon(String name, String description, Long manufacturerId) {
       Manufacturer manufacturer = manufacturerService.
               findAll().stream().
               filter(m->m.getId().equals(manufacturerId)).
               findFirst().
               get();

       return Optional.of(balloonRepository.save(new Balloon(name,description,manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        balloonRepository.deleteById(id);
    }

    public List<Balloon> findBalloonByCountryName(String countryName){
    return balloonRepository.findBalloonByManufacturer(manufacturerService.findByCountryName(countryName));
    }


}
