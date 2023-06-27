package com.example.MusicPlayer.filter;

import com.example.MusicPlayer.model.UserToken;
import com.example.MusicPlayer.model.Users;
import com.example.MusicPlayer.service.UsersService;
import com.example.MusicPlayer.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private UsersService userService;


    public CustomAuthenticationFilter(UsersService userService,AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // sending username, password to authentication manager
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);

    }

    @Override
    protected void successfulAuthentication
            (HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        System.out.println("\u001B[32m" + "User Authenticate Successfully!!" + "\u001B[0m");
        Users loggedInUser = userService.getUserByEmail(authResult.getName());
        String token = JwtUtils.createJwtToken(((User) authResult.getPrincipal()).getUsername());
        UserToken userToken = UserToken.builder().user(loggedInUser).token(token).build();

        // saving uuid & updating cookies
        userService.saveToken(userToken);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("token", token);
        hashMap.put("email", request.getParameter("username"));
//        hashMap.put("auth", authResult.getAuthorities().iterator().next().getAuthority());
//        BeanUtils.copyProperties(hashMap, response.getOutputStream());
        new ObjectMapper().writeValue(response.getOutputStream(), hashMap);
    }
}
