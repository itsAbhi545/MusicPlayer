package com.example.MusicPlayer.service;

import com.example.MusicPlayer.CustomExceptions.ApiException;
import com.example.MusicPlayer.model.Authority;
import com.example.MusicPlayer.model.UserRole;
import com.example.MusicPlayer.model.UserToken;
import com.example.MusicPlayer.model.Users;
import com.example.MusicPlayer.repo.UserTokenRepo;
import com.example.MusicPlayer.repo.UsersRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService  {
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
        UserRole userRole = rolesService.findUserRoleByUser(user);
        if(userRole.isDeleted()){
            throw new ApiException(HttpStatus.UNAUTHORIZED,"User is Deleted");
        }
        Collection<Authority> authorities=new ArrayList<>();
        authorities.add(new Authority("ROLE_" + userRole.getRole().getRoleName()));
        return new User(user.getEmail(),user.getPassword(),authorities);
    }
}
