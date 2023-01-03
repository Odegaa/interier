package uz.interier.services;

import uz.interier.models.Address;
import uz.interier.payloads.AddressDto;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();

    Address getAddressById(Long addressId);

    Address getAddressByStreetName(String streetName);

    ApiResponse addAddress(AddressDto addressDto);

    ApiResponse updateAddressById(Long addressId, AddressDto addressDto);

    ApiResponse deleteAddress(Long addressId);
}