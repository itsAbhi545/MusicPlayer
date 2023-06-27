package com.example.MusicPlayer.filter;

import com.example.MusicPlayer.model.Authority;
import com.example.MusicPlayer.model.Users;
import com.example.MusicPlayer.service.UsersService;
import com.example.MusicPlayer.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private UsersService userService;

    public  CustomAuthorizationFilter(UsersService userService){
        this.userService=userService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getServletPath());

        if(request.getServletPath().contains("/user/")) {
            String token=request.getHeader("Authorization").substring(7);
            if(token==null || userService.findUserFromToken(token)==null){
                Map<String,String> error=new HashMap<>();
                error.put("error_message","Please provide valid token");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
            else if(token!=null) {
                String user = JwtUtils.verifyJwtToken(token);
                System.out.println(user+"/jefgegfyugeryfg");
                Users temp= userService.getUserByEmail(user);
                Collection<Authority> authorities=new ArrayList<>();
                authorities.add(new Authority("USER"));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(temp.getId(),null,authorities);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request,response);
            }
        }
        else {
            System.out.println("savhvsfajvfsaabjnbsfa");
            filterChain.doFilter(request,response);
        }
    }
}
