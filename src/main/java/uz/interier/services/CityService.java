package uz.interier.services;

import uz.interier.models.City;
import uz.interier.payloads.CityDto;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    City getCityById(Long cityId);

    City getCityByCityName(String cityName);

    ApiResponse addCity(CityDto cityDto);

    ApiResponse updateCityById(Long cityId, CityDto cityDto);

    ApiResponse deleteCity(Long cityId);
}