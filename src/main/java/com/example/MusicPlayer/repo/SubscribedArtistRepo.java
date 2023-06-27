package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.SubscribedArtist;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SubscribedArtistRepo extends JpaRepository<SubscribedArtist,Integer> {
    @Query("Select sa from SubscribedArtist sa where sa.user.id = ?1 AND sa.artist.id = ?2")
    SubscribedArtist findByUserIdAndArtistId(long userId,long artistId);
}
