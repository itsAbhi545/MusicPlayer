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
public class UserServiceImpl  {


}
