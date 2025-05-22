package com.project.recipe.Controller;

import com.project.recipe.Dto.UserDto;
import com.project.recipe.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto){
        String savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
