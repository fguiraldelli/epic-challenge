package fguiraldelli.epicchallenge.service;

import fguiraldelli.epicchallenge.dto.UserDto;

public interface UserService {

    UserDto registerUser(UserDto user);
    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
