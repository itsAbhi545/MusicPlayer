package com.example.MusicPlayer.service;

import com.example.MusicPlayer.CustomExceptions.ApiException;
import com.example.MusicPlayer.dto.ArtistListDto;
import com.example.MusicPlayer.model.Authority;
import com.example.MusicPlayer.model.UserToken;
import com.example.MusicPlayer.model.Users;
import com.example.MusicPlayer.repo.UserTokenRepo;
import com.example.MusicPlayer.repo.UsersRepo;
import com.example.MusicPlayer.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.example.MusicPlayer.MusicPlayerApplication.passwordEncoder;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {
    //private final JavaMailSender javaMailSender;
    private final UsersRepo usersRepo;
    private final UserTokenRepo tokenRepo;
    private final RolesService rolesService;
    public Users getUserByEmail(String email){
        return usersRepo.findByEmail(email);
    }
    public Users getUserByUuid(String uuid){
        return usersRepo.findByUuid(uuid);
    }
    public Users saveUser(Users user){
        return usersRepo.save(user);
    }
    public void saveToken(UserToken userToken){
        tokenRepo.save(userToken);
    }
    public Users findUserFromToken(String token) {
        UserToken userToken= tokenRepo.findByToken(token);
        if(userToken==null){
            return null;
        }
        return userToken.getUser();
    }
    public Users register(Users newUser) {
        String uuid= UUID.randomUUID().toString();
        newUser.setUuid(uuid);
        newUser.setVerified(false);
        newUser.setPassword(passwordEncoder().encode(newUser.getPassword()));
       // sendVerificationLink(newUser.getEmail());
        return this.saveUser(newUser);
    }
    public void sendVerificationLink(String email){
        Users user = this.getUserByEmail(email);
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
        Users user= this.getUserByUuid(uuid);
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
    //    javaMailSender.send(message);
    }
    public Users getUserReferenceById(long userId){
        return usersRepo.getReferenceById(userId);
    }
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String authHeader=request.getHeader("Authorization").substring(7);
        System.out.println(authHeader);
        tokenRepo.deleteUserTokenByToken(authHeader);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= usersRepo.findByEmail(username);
        if(user==null){
            throw new ApiException(HttpStatus.NOT_FOUND,"User does not exist");
        }
        if(!user.isVerified()){
            throw new ApiException(HttpStatus.UNAUTHORIZED,"User is not verified");
        }
//        UserRole userRole = rolesService.findUserRoleByUser(user);
//        if(userRole.isDeleted()){
//            throw new ApiException(HttpStatus.UNAUTHORIZED,"User is Deleted");
//        }
        Collection<Authority> authorities=new ArrayList<>();
       // authorities.add(new Authority("ROLE_" + userRole.getRole().getRoleName()));
        return new User(user.getEmail(),user.getPassword(),authorities);
    }
    public List<ArtistListDto> getArtistList(){
        return usersRepo.getArtistList(2);
    }

}
