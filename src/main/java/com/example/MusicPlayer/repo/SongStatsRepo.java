package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.SongStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SongStatsRepo extends JpaRepository<SongStats,Integer> {
    @Transactional @Modifying
    @Query("update SongStats ss set ss.views = ss.views + ?1 where ss.id = ?2")
    void updateViewsInSongStats(long views,int songId);
    @Transactional @Modifying
    @Query("update SongStats ss set ss.likes = ss.likes + ?1,ss.dislikes = ss.dislikes + ?2 where ss.id = ?3")
    void updateLikeAndDislikeInSongStats(long like,long dislike,int songId);
}
