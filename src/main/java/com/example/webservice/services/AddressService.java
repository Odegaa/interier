package com.example.webservice.services;

import com.example.webservice.models.Address;
import com.example.webservice.payloads.AddressDto;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();

    Address getAddressById(Long addressId);

    Address getAddressByStreetName(String streetName);

    ApiResponse addAddress(AddressDto addressDto);

    ApiResponse updateAddressById(Long addressId, AddressDto addressDto);

    ApiResponse deleteAddress(Long addressId);
}