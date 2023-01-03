package com.example.webservice.services.impls;

import com.example.webservice.models.Address;
import com.example.webservice.models.Card;
import com.example.webservice.models.User;
import com.example.webservice.models.templates.Roles;
import com.example.webservice.models.templates.Status;
import com.example.webservice.payloads.UserDto;
import com.example.webservice.repositories.AddressRepository;
import com.example.webservice.repositories.CardRepository;
import com.example.webservice.repositories.UserRepository;
import com.example.webservice.services.UserService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CardRepository cardRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           AddressRepository addressRepository,
                           CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cardRepository = cardRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getUserByFirstName(String firstName) {
        User byFirstName = userRepository.findByFirstName(firstName);
        if (byFirstName == null) {
            return null;
        }
        return userRepository.getUserByFirstName(firstName);
    }

    @Override
    public User getUserByLastName(String lastName) {
        User byLastName = userRepository.findByLastName(lastName);
        if (byLastName == null) {
            return null;
        }
        return userRepository.getUserByLastName(lastName);
    }

    @Override
    public ApiResponse addUser(UserDto userDto) {
        boolean byPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (byPhoneNumber) {
            return new ApiResponse("This phone number already exist!", false);
        }
        boolean byEmail = userRepository.existsByEmail(userDto.getEmail());
        if (byEmail) {
            return new ApiResponse("This email already exist!", false);
        }

        List<Card> cardList = new ArrayList<>();

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        Optional<Address> optionalAddress = addressRepository.findById(userDto.getAddressId());
        optionalAddress.ifPresent(user::setAddress);

        for (Long value : userDto.getCardId()) {
            Optional<Card> optionalCard = cardRepository.findById(value);
            optionalCard.ifPresent(cardList::add);
        }
        user.setCards(cardList);
        user.setRoles(Roles.USER);
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);
        return new ApiResponse("User successfully SAVED!", true);
    }

    @Override
    public ApiResponse updateUserById(Long userId, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Card> cardList = new ArrayList<>();
            User user = userOptional.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());
            Optional<Address> optionalAddress = addressRepository.findById(userDto.getAddressId());
            optionalAddress.ifPresent(user::setAddress);

            for (Long value : userDto.getCardId()) {
                Optional<Card> optionalCard = cardRepository.findById(value);
                optionalCard.ifPresent(cardList::add);
            }
            user.setCards(cardList);
            userRepository.save(user);
            return new ApiResponse("User successfully UPDATED!", true);
        }
        return new ApiResponse("User not found", false);
    }

    @Override
    public ApiResponse deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            return new ApiResponse("User deleted!", true);
        }
        return new ApiResponse("User not found!", false);
    }
}