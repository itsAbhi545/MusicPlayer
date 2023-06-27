package com.example.MusicPlayer.service;

import com.example.MusicPlayer.model.UserRole;
import com.example.MusicPlayer.repo.UserRoleRepo;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    private final UserRoleRepo userRoleRepo;

    public UserRoleService(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }
    public UserRole findUserRoleByUserIdAndRoleId(int userId){
        return userRoleRepo.findUserRoleByUserIdAndRoleId(userId,2);
    }
}
