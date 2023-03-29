package fguiraldelli.epicchallenge.service.impl;

import fguiraldelli.epicchallenge.dto.UserDto;
import fguiraldelli.epicchallenge.entity.User;
import fguiraldelli.epicchallenge.exception.CustomerAlreadyExistsException;
import fguiraldelli.epicchallenge.exception.ResourceNotFoundException;
import fguiraldelli.epicchallenge.mapper.UserMapper;
import fguiraldelli.epicchallenge.repository.UserRepository;
import fguiraldelli.epicchallenge.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto user) {
        Optional<User> optionalUser = userRepository.getByUserName(user.getUserName());

        if(optionalUser.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists in database");
        }

        User newUser = UserMapper.mapToUser(user);

        User savedUser = userRepository.save(newUser);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser= userRepository.findById(user.getCustomer_id_user()).orElseThrow(
                () -> new ResourceNotFoundException("User","customer_id_user", user.getCustomer_id_user().toString())
        );

        existingUser.setCustomerIdUser(user.getCustomer_id_user());
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

        User existingUser= userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","customer_id_user", userId.toString())
        );

        userRepository.deleteById(userId);

    }
}
