package com.example.MusicPlayer.controller;

import com.example.MusicPlayer.dto.ApiResponse;
import com.example.MusicPlayer.model.Users;
import com.example.MusicPlayer.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final UsersService usersService;
    @PostMapping("/create-user-account")
    public ApiResponse signup(Users user){
        return new ApiResponse("User successfully registered",usersService.register(user), HttpStatus.CREATED);
    }
}
