package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.dto.ArtistListDto;
import com.example.MusicPlayer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepo extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    Users findByUuid(String uuid);

    @Query("Select new com.example.MusicPlayer.dto.ArtistListDto(u.id,u.firstName,u.lastName,u.userImageUrl) from Users u INNER JOIN u.userRole ur WHERE ur.role.rId = ?1")
    List<ArtistListDto> getArtistList(int roleId);

}
