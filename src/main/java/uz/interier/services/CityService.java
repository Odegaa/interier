package com.example.webservice.services;

import com.example.webservice.models.City;
import com.example.webservice.payloads.CityDto;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    City getCityById(Long cityId);

    City getCityByCityName(String cityName);

    ApiResponse addCity(CityDto cityDto);

    ApiResponse updateCityById(Long cityId, CityDto cityDto);

    ApiResponse deleteCity(Long cityId);
}