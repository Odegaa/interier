package uz.interier.controllers;

import uz.interier.models.City;
import uz.interier.payloads.CityDto;
import uz.interier.services.CityService;
import uz.interier.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addCity(@Valid @RequestBody CityDto cityDto) {
        ApiResponse apiResponse = service.addCity(cityDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(service.getAllCities());
    }

    @GetMapping("get/{cityId}")
    public HttpEntity<City> getCityById(@PathVariable Long cityId) {
        return ResponseEntity.ok(service.getCityById(cityId));
    }

    @GetMapping("/{cityName}")
    public HttpEntity<City> getCityByName(@PathVariable String cityName) {
        return ResponseEntity.ok(service.getCityByCityName(cityName));
    }

    @PutMapping("/{cityId}")
    public HttpEntity<?> updateCityById(@PathVariable Long cityId,
                                        @Valid @RequestBody CityDto cityDto) {
        ApiResponse apiResponse = service.updateCityById(cityId, cityDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping("/{cityId}")
    public HttpEntity<?> deleteCityById(@PathVariable Long cityId) {
        ApiResponse apiResponse = service.deleteCity(cityId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }
}