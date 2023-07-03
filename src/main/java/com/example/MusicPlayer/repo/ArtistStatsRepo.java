package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.ArtistStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ArtistStatsRepo extends JpaRepository<ArtistStats,Long> {
    @Transactional @Modifying
    @Query("update ArtistStats ars set ars.subscribers = ars.subscribers + ?1 where ars.id = ?2")
    void updateArtistSubscribersInArtistStats(long subscribe,long artistId);
    @Transactional @Modifying
    @Query("update ArtistStats ars set ars.uploads = ars.uploads + ?1 where ars.id = ?2")
    void updateArtistUploadsInArtistStats(long uploads,long artistId);
    @Transactional @Modifying
    @Query("update ArtistStats ars set ars.views = ars.views + ?1 where ars.id = ?2")
    void updateViewsInArtistStats(long views,long artistId);
}
