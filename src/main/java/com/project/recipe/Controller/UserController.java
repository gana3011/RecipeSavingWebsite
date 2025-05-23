package com.project.recipe.Controller;

import com.project.recipe.Dto.UserDto;
import com.project.recipe.Dto.UserResponseDto;
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
    public ResponseEntity<String> signup(@RequestBody UserDto userDto){
        String savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
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
