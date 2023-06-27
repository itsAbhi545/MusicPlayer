package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.UserRole;
import com.example.MusicPlayer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    UserRole findByUser(Users userId);
    @Query("Select ur from UserRole ur where ur.user.id = ?1 AND ur.role.rId = ?2")
    UserRole findUserRoleByUserIdAndRoleId(long userId,int roleId);
}
