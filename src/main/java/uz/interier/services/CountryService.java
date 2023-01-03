package uz.interier.services;

import uz.interier.models.Country;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountries();

    Country getCountryById(Long countryId);

    Country getCountryByCountryName(String countryName);

    ApiResponse addCountry(Country country);

    ApiResponse updateCountry(Long countryId, Country country);

    ApiResponse deleteCountry(Long countryId);
}