package com.project.recipe.Service;

import com.project.recipe.Dto.UserDto;
import com.project.recipe.Entity.User;
import com.project.recipe.Mapper.UserMapper;
import com.project.recipe.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public String createUser(UserDto userDto){
        User user = UserMapper.convertToUser(userDto);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        return "User Created";
    }
}
