package com.example.MusicPlayer.service;

import com.example.MusicPlayer.CustomExceptions.ApiException;
import com.example.MusicPlayer.model.Users;
import com.example.MusicPlayer.repo.UsersRepo;
import com.example.MusicPlayer.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.MusicPlayer.MusicPlayerApplication.passwordEncoder;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserServiceImpl userService;
    private final JavaMailSender javaMailSender;
    public Users register(Users newUser) {
        String uuid= UUID.randomUUID().toString();
        newUser.setUuid(uuid);
        newUser.setPassword(passwordEncoder().encode(newUser.getPassword()));
        sendVerificationLink(newUser.getEmail());
        return userService.saveUser(newUser);
    }
    public void sendVerificationLink(String email){
        Users user = userService.getUserByEmail(email);
        if(user==null){
            throw new ApiException(HttpStatus.NOT_FOUND,"No user by this email, consider signing up");
        }
        String token = JwtUtils.createJwtToken(user.getUuid());
        String link = "http://localhost:8081/eNaukri/verify/"+token+"/"+user.getUuid();
        String to= user.getEmail();
        String subject="eNaukri job portal - Verify your account";
        String body="Click the link to verify your account " +link;
        sendEmail(to,subject,body);
    }
    public void verifyUserAccount(String token, String uuid){
        String decodedToken = JwtUtils.verifyJwtToken(token);
        Users user= userService.getUserByUuid(uuid);
        if(decodedToken==uuid){
            user.setVerified(true);
        }
        else{
            throw new ApiException(HttpStatus.FORBIDDEN,"The token has expired consider making a new request");
        }
    }
    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("harmanjyot.singh@chicmic.co.in");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
