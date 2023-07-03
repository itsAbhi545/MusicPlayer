package com.example.MusicPlayer;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MusicPlayerApplication {

	public static void main(String[] args) {
		Stripe.apiKey = "sk_test_51N4K6TSEogU4odysj4Pz9SaDmFBcftfIq2sWqvXrVRMxkBTKqhd8Gk0lKcnUFIAELGixCZBO2VfG5PVLqnWIRZUh00b8BPqLH1";
		SpringApplication.run(MusicPlayerApplication.class, args);
	}
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
