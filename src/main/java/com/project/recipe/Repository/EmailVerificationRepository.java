package com.project.recipe.Repository;

import com.project.recipe.Entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification,Long> {
    Optional<EmailVerification> findByEmail(String email);
}
