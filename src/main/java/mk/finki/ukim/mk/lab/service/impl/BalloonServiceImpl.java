package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryBalloonRepository;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryManufacturerRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByName(String name) {
        return balloonRepository.findAllByName(name);
    }

    @Override
    public Optional<Balloon> findBalloonById(Long id) {
        return balloonRepository.findById(id);
    }

    @Override
    public Optional<Balloon> saveBalloon(String name, String description, Long manufacturerId) {
       Manufacturer manufacturer = manufacturerRepository.
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

    public List<Balloon> findBalloonByCountryName(String country){
    return balloonRepository.findBalloonByManufacturer(new Manufacturer(new Country(country)));
    }


}
