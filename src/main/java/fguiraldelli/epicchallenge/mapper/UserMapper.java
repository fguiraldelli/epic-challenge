package fguiraldelli.epicchallenge.mapper;

import fguiraldelli.epicchallenge.dto.UserDto;
import fguiraldelli.epicchallenge.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto (User user){
        UserDto userDto = new UserDto(
                user.getCustomerIdUser(),
                user.getUserName(),
                user.getPassword()
        );
        return userDto;
    }

    public static User mapToUser (UserDto userDto){
        User user = new User(
                userDto.getCustomer_id_user(),
                userDto.getUserName(),
                userDto.getPassword()
        );
        return user;
    }
}
