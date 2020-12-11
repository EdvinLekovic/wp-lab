package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon,Long> {
    void deleteById(Long id);
    List<Balloon> findAllByNameContains(String name);
    List<Balloon> findBalloonByManufacturer(Manufacturer manufacturer);
    List<Balloon> findAllByDescriptionContains(String description);
}
