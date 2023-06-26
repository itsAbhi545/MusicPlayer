package com.example.MusicPlayer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="userRoles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    Roles role;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    Users user;
    @Column(name = "deleted")
    private boolean deleted = false;
}
