package uz.interier.services.impls;

import uz.interier.models.Address;
import uz.interier.models.City;
import uz.interier.models.templates.Status;
import uz.interier.payloads.AddressDto;
import uz.interier.repositories.AddressRepository;
import uz.interier.repositories.CityRepository;
import uz.interier.services.AddressService;
import uz.interier.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

    @Override
    public Address getAddressByStreetName(String streetName) {
        Address byStreetName = addressRepository.findByStreetName(streetName);
        if (byStreetName == null) {
            return null;
        }
        return getAddressByStreetName(streetName);
    }

    @Override
    public ApiResponse addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setStreetName(addressDto.getStreetName());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setStatus(Status.ACTIVE);
        Optional<City> optionalCity = cityRepository.findById(addressDto.getCityId());
        if (optionalCity.isPresent()) {
            address.setCityId(optionalCity.get());
            addressRepository.save(address);
            return new ApiResponse("Address successfully SAVED!", true);
        }
        return new ApiResponse("City not found!", false);
    }

    @Override
    public ApiResponse updateAddressById(Long addressId, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setStreetName(addressDto.getStreetName());
            address.setHouseNumber(addressDto.getHouseNumber());
            address.setStatus(Status.ACTIVE);
            Optional<City> optionalCity = cityRepository.findById(addressDto.getCityId());
            if (optionalCity.isPresent()) {
                address.setCityId(optionalCity.get());
                addressRepository.save(address);
                return new ApiResponse("Address successfully UPDATED!", true);
            }
            return new ApiResponse("City not found!", false);
        }
        return new ApiResponse("Address not found!", false);
    }

    @Override
    public ApiResponse deleteAddress(Long addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            addressRepository.deleteById(addressId);
            return new ApiResponse("Address deleted!", true);
        }
        return new ApiResponse("Address not found!", false);
    }
}