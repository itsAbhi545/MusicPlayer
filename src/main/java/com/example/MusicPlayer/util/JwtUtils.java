package com.example.MusicPlayer.util;

import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

import static com.example.MusicPlayer.config.SecurityConfig.EXPIRATION_TIME;
import static com.example.MusicPlayer.config.SecurityConfig.SECRET;
import com.auth0.jwt.JWT;

public class JwtUtils {
    public static String createJwtToken(String subject){
        String token = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET.getBytes()));
        return token;
    }
    public static String verifyJwtToken(String token){
        String decodedToken = JWT.require(Algorithm.HMAC256(SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();
        return decodedToken;
    }
}
