package com.project.recipe.Mapper;

import com.project.recipe.Dto.UserDto;
import com.project.recipe.Dto.UserResponseDto;
import com.project.recipe.Entity.Recipe;
import com.project.recipe.Entity.User;

import java.util.Set;

public class UserMapper {
    public static UserDto convertToDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static User convertToUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserResponseDto convertToUserResponse(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
