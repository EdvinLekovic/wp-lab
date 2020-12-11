package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findByName(String countryName);
}
