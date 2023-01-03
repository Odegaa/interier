package com.example.webservice.services.impls;

import com.example.webservice.models.Country;
import com.example.webservice.models.templates.Status;
import com.example.webservice.repositories.CountryRepository;
import com.example.webservice.services.CountryService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long countryId) {
        return countryRepository.findById(countryId).orElse(null);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        Country byCountryName = countryRepository.findByCountryName(countryName);
        if (byCountryName == null) {
            return null;
        }
        return countryRepository.getCountryByCountryName(countryName);
    }

    @Override
    public ApiResponse addCountry(Country country) {
        boolean byCountryName = countryRepository.existsByCountryName(country.getCountryName());
        if (byCountryName) {
            return new ApiResponse("This country name already exist!", false);
        }
        Country newCountry = new Country();
        newCountry.setCountryName(country.getCountryName());
        newCountry.setStatus(Status.ACTIVE);

        countryRepository.save(newCountry);
        return new ApiResponse("Country successfully SAVED!", true);
    }

    @Override
    public ApiResponse updateCountry(Long countryId, Country country) {
        Optional<Country> countryOptional = countryRepository.findById(countryId);
        if (countryOptional.isPresent()) {
            boolean byCountryName = countryRepository.existsByCountryName(country.getCountryName());
            if (byCountryName) {
                return new ApiResponse("This country name already exist!", false);
            }
            Country updatingCountry = countryOptional.get();
            updatingCountry.setCountryName(country.getCountryName());
            updatingCountry.setStatus(Status.ACTIVE);
            countryRepository.save(updatingCountry);
            return new ApiResponse("Country successfully UPDATED!", true);
        }
        return new ApiResponse("Country not found!", false);
    }

    @Override
    public ApiResponse deleteCountry(Long countryId) {
        Optional<Country> countryOptional = countryRepository.findById(countryId);
        if (countryOptional.isPresent()) {
            countryRepository.deleteById(countryId);
            return new ApiResponse("Country successfully DELETED!", true);
        }
        return new ApiResponse("Country not found!", false);
    }
}