package com.example.MusicPlayer.service;
import com.example.MusicPlayer.model.Roles;
import com.example.MusicPlayer.model.UserRole;
import com.example.MusicPlayer.model.Users;
import com.example.MusicPlayer.repo.RoleRepo;
import com.example.MusicPlayer.repo.UserRoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesService {
    private final RoleRepo roleRepo;
    private final UserRoleRepo userRoleRepo;
    public Roles saveRoles(Roles roles) {
        return roleRepo.save(roles);
    }
    public Roles getRoleByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    //USERROLE
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }
    public UserRole findUserRoleByUser(Users user) {
        return userRoleRepo.findByUser(user);
    }

    public UserRole addRoleToUser(String roleName, Users users) {
        Roles roles = getRoleByRoleName(roleName);
        UserRole userRole = UserRole.builder()
                .user(users)
                .role(roles)
                .build();
        return saveUserRole(userRole);
    }
}
