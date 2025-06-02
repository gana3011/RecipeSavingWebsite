package com.project.recipe.Controller;

import com.project.recipe.Dto.ResponseDto;
import com.project.recipe.Dto.UserDto;
import com.project.recipe.Dto.UserResponseDto;
import com.project.recipe.Dto.OtpRequestDto;
import com.project.recipe.Service.EmailVerificationService;
import com.project.recipe.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class UserController {
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody UserDto userDto){
        ResponseDto response = userService.createUser(userDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/verify")
    public ResponseEntity<ResponseDto> verify(@RequestBody OtpRequestDto otpRequestDto){
        ResponseDto response = userService.verifyOtp(otpRequestDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/resend")
    public ResponseEntity<ResponseDto> resend(@RequestBody Map<String, String> request){
        String email = request.get("email").trim();
        ResponseDto response = userService.resendOtp(email);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody UserDto userDto){
        UserResponseDto currentUser = userService.signinUser(userDto);
        Map<String,Object> response = new HashMap<>();
        response.put("message","Sign in successfull");
        response.put("user",currentUser);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
