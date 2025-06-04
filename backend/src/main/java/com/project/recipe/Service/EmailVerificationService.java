package com.project.recipe.Service;
import com.project.recipe.Entity.EmailVerification;
import com.project.recipe.Repository.EmailVerificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@AllArgsConstructor
@Service
public class EmailVerificationService {
    private EmailVerificationRepository repo;
    private JavaMailSender javaMailSender;

    public boolean sendOtp(String email){
        String otp = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);
        EmailVerification ev = repo.findByEmail(email).orElse(new EmailVerification());
        ev.setEmail(email);
        ev.setOtp(otp);
        ev.setExpiryTime(expiry);
        repo.save(ev);
        return sendMail(email,otp);
    }

    private boolean sendMail(String email,String otp){
        try {
            SimpleMailMessage message= new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your verification Otp");
            message.setText("Your OTP:"+otp);
            message.setText("Make sure to enter the OTP within 5 minutes");
            javaMailSender.send(message);
            return true;
        }
        catch (Exception e){
            System.out.println("Error sending email: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyOtp(String email, String otp){
        return repo.findByEmail(email).filter(ev->ev.getOtp().equals(otp) && ev.getExpiryTime().isAfter(LocalDateTime.now())).isPresent();
    }
}
