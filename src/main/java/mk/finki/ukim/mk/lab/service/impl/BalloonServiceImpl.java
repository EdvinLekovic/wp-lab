package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return balloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public Optional<Balloon> findBalloonById(Long id) {
        return balloonRepository.findBalloonById(id);
    }

    @Override
    public Optional<Balloon> saveBalloon(String name, String description, Long manufacturerId) {
       Manufacturer manufacturer = manufacturerRepository.
               findAll().stream().
               filter(m->m.getId().equals(manufacturerId)).
               findFirst().
               get();
       return balloonRepository.save(name,description,manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        balloonRepository.deleteById(id);
    }

    public List<Balloon> findBalloonByCountryName(String country){
    return balloonRepository.findBalloonByCountryName(country);
    }


}
