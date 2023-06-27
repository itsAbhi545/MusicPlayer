package com.example.MusicPlayer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@RequiredArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private boolean verified;
    private String uuid;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user")
    @JsonIgnore
    private UserRole userRole;
    private String userImageUrl;
}
