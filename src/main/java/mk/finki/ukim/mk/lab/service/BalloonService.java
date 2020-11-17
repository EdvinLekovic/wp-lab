package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);
    Optional<Balloon> findBalloonById(Long id);
    Optional<Balloon> saveBalloon(String name,String description,Long manufacturerId);
    void deleteById(Long id);
    List<Balloon> findBalloonByCountryName(String country);
}
