package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.SubscribedArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface SubscribedArtistRepo extends JpaRepository<SubscribedArtist,Integer> {
    @Query("Select sa from SubscribedArtist sa where sa.user.id = ?1 AND sa.artist.id = ?2")
    SubscribedArtist findByUserIdAndArtistId(long userId,long artistId);
    @Transactional @Modifying
    @Query("Delete SubscribedArtist sa where sa.artist.id = ?1")
    int unsubscribeArtistWithId(long artistId);
}
