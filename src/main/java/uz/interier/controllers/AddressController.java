package com.example.webservice.controllers;

import com.example.webservice.models.Address;
import com.example.webservice.payloads.AddressDto;
import com.example.webservice.services.AddressService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addAddress(@Valid @RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = service.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(service.getAllAddresses());
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long addressId) {
        return ResponseEntity.ok(service.getAddressById(addressId));
    }

    @GetMapping("/{streetName}")
    public ResponseEntity<Address> getAddressByStreetName(@PathVariable String streetName) {
        return ResponseEntity.ok(service.getAddressByStreetName(streetName));
    }

    @PutMapping("/{addressId}")
    public HttpEntity<?> updateAddress(@PathVariable Long addressId,
                                       @Valid @RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = service.updateAddressById(addressId, addressDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping("/{addressId}")
    public HttpEntity<?> deleteAddress(@PathVariable Long addressId) {
        ApiResponse apiResponse = service.deleteAddress(addressId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

}