package com.project.recipe.Service;
import com.project.recipe.Dto.UserDto;
import com.project.recipe.Dto.UserResponseDto;
import com.project.recipe.Entity.User;
import com.project.recipe.Mapper.UserMapper;
import com.project.recipe.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    public String createUser(UserDto userDto){
        User user = UserMapper.convertToUser(userDto);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        return "User Created";
    }

    public UserResponseDto signinUser(UserDto userDto){
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String hashedPass = user.getPassword();
        boolean matches = passwordEncoder.matches(userDto.getPassword(),hashedPass);
        if(!matches){
            throw new BadCredentialsException("Invalid email or password");
        }
        String jwt = jwtUtil.generateToken(userDto);
        return UserMapper.convertToUserResponse(user,jwt);
    }
}
