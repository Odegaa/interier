package com.example.webservice.controllers;

import com.example.webservice.models.Country;
import com.example.webservice.services.CountryService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addCountry(@Valid @RequestBody Country country) {
        ApiResponse apiResponse = service.addCountry(country);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(service.getAllCountries());
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long countryId) {
        return ResponseEntity.ok(service.getCountryById(countryId));
    }

    @GetMapping("/{countryName}")
    public ResponseEntity<Country> getCountryByCountryName(@PathVariable String countryName) {
        return ResponseEntity.ok(service.getCountryByCountryName(countryName));
    }

    @PutMapping("/{countryId}")
    public HttpEntity<?> updateCountryById(@PathVariable Long countryId,
                                           @Valid @RequestBody Country country) {
        ApiResponse apiResponse = service.updateCountry(countryId, country);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping("/{countryId}")
    public HttpEntity<?> deleteCountryById(@PathVariable Long countryId) {
        ApiResponse apiResponse = service.deleteCountry(countryId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

}