package com.project.recipe.Repository;

import com.project.recipe.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
//    Optional<User> findById(Long id);
    boolean existsByEmail(String email);
}