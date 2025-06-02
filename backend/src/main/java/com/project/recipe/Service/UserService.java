package com.project.recipe.Service;
import com.project.recipe.Dto.*;
import com.project.recipe.Entity.User;
import com.project.recipe.Mapper.UserMapper;
import com.project.recipe.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private EmailVerificationService emailVerificationService;
    private JwtUtil jwtUtil;

    private ResponseDto responseDto(HttpStatus status, String message) {
        return new ResponseDto(status, message);
    }

    public ResponseDto createUser(UserDto userDto) {
        User user = UserMapper.convertToUser(userDto);
        Optional<User> existingUserOpt = userRepository.findByEmail(userDto.getEmail());
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            if(existingUser.isVerified()){
                throw new BadCredentialsException("Email already exists and is verified, so signin");
            }
            else{
                boolean sent = emailVerificationService.sendOtp(user.getEmail());
                if (sent) {
                    return responseDto(HttpStatus.OK, "User already exists but not verified. OTP re-sent to email.");
                } else {
                    return responseDto(HttpStatus.BAD_GATEWAY, "Failed to resend OTP.");
                }
            }
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        boolean sent = emailVerificationService.sendOtp(user.getEmail());
        if(sent){
            return responseDto(HttpStatus.CREATED, "Enter the Otp from the Email");
        }
        else{
            return responseDto(HttpStatus.BAD_GATEWAY, "Error sending email");
        }
    }

    public ResponseDto resendOtp(String email){
        boolean sent = emailVerificationService.sendOtp(email);
        if(sent){
            return responseDto(HttpStatus.ACCEPTED,"Otp resent to email");
        }
        else{
            return responseDto(HttpStatus.BAD_GATEWAY, "Error sending email");
        }
    }

    public ResponseDto verifyOtp(OtpRequestDto otpRequestDto){
        boolean isVerified = emailVerificationService.verifyOtp(otpRequestDto.getEmail(), otpRequestDto.getOtp());
        if(isVerified){
            User user = userRepository.findByEmail(otpRequestDto.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            user.setVerified(true);
            userRepository.save(user);
            return responseDto(HttpStatus.ACCEPTED, "Otp Verified Successfully");
        }
        return responseDto(HttpStatus.BAD_REQUEST, "Enter the correct OTP");
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
