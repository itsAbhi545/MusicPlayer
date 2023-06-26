package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.UserRole;
import com.example.MusicPlayer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    UserRole findByUser(Users userId);
}
