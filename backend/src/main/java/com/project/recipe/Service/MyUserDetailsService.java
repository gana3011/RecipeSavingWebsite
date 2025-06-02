package com.project.recipe.Service;

import com.project.recipe.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.project.recipe.Entity.User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found with email:"+email));
        return new User(user.getEmail(), user.getPassword(), user.isVerified(), true, true, true, Collections.emptyList());
    }
}
