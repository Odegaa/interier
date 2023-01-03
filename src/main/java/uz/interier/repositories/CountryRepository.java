package uz.interier.repositories;

import uz.interier.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByCountryName(String countryName);

    Country getCountryByCountryName(String countryName);


    boolean existsByCountryName(String countryName);

}