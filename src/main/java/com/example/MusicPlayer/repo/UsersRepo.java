package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    Users findByUuid(String uuid);
}
