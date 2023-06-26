package com.example.MusicPlayer.config;

import com.example.MusicPlayer.filter.CustomAuthenticationFilter;
import com.example.MusicPlayer.filter.CustomAuthorizationFilter;
import com.example.MusicPlayer.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static com.example.MusicPlayer.MusicPlayerApplication.passwordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {
    public static final String SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    public static final long EXPIRATION_TIME = 86400_000; // 24 hrs

    private final UserDetailsService userDetailsService;
    @Autowired
    private final UserServiceImpl userService;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        //Filter objects
        CustomAuthenticationFilter authenticationFilter=
                new CustomAuthenticationFilter(userService,authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));
        authenticationFilter.setFilterProcessesUrl("/api/login");
//        CustomAuthenticationFilter authenticationFilter1=
//                new CustomAuthenticationFilter(userService,authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));
//        authenticationFilter.setFilterProcessesUrl("/company-login");
        http.csrf(csrf -> {
            try {
                csrf.disable().cors(cors-> cors.configurationSource(corsConfigurationSource()).disable());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        //csrf+session
        http.csrf(csrf->csrf.disable());
        http.sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //permits
//        http.authorizeHttpRequests(authorizeHttpRequests->authorizeHttpRequests.requestMatchers("/user/**").hasAnyAuthority("USER"));
        http.authorizeHttpRequests(authorizeHttpRequests->authorizeHttpRequests.anyRequest().permitAll());
        //adding filters
        http.addFilterBefore(new CustomAuthorizationFilter(userService), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(authenticationFilter);

        //building
        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");

        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}