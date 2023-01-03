package uz.interier.services.impls;

import uz.interier.models.City;
import uz.interier.models.Country;
import uz.interier.models.templates.Status;
import uz.interier.payloads.CityDto;
import uz.interier.repositories.CityRepository;
import uz.interier.repositories.CountryRepository;
import uz.interier.services.CityService;
import uz.interier.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElse(null);
    }

    @Override
    public City getCityByCityName(String cityName) {
        City byCityName = cityRepository.findCityByCityName(cityName);
        if (byCityName == null) {
            return null;
        }
        return cityRepository.getCityByCityName(cityName);
    }

    @Override
    public ApiResponse addCity(CityDto cityDto) {
        boolean byCityName = cityRepository.existsByCityName(cityDto.getCityName());
        if (byCityName) {
            return new ApiResponse("This city name already exist!", false);
        }
        City city = new City();
        city.setCityName(cityDto.getCityName());
        city.setStatus(Status.ACTIVE);
        Optional<Country> countryOptional = countryRepository.findById(cityDto.getCountryId());
        if (countryOptional.isPresent()) {
            city.setCountryId(countryOptional.get());
            cityRepository.save(city);
            return new ApiResponse("City successfully SAVED!", true);
        }
        return new ApiResponse("Country not found!", false);
    }

    @Override
    public ApiResponse updateCityById(Long cityId, CityDto cityDto) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            city.setCityName(cityDto.getCityName());
            city.setStatus(Status.ACTIVE);
            Optional<Country> countryOptional = countryRepository.findById(cityDto.getCountryId());
            if (countryOptional.isPresent()) {
                city.setCountryId(countryOptional.get());
                cityRepository.save(city);
                return new ApiResponse("City successfully UPDATED!", true);
            }
            return new ApiResponse("Country not found!", false);
        }
        return new ApiResponse("City not found!", false);
    }

    @Override
    public ApiResponse deleteCity(Long cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            cityRepository.deleteById(cityId);
            return new ApiResponse("City successfully DELETED!", true);
        }
        return new ApiResponse("City not found!", false);
    }
}