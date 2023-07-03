package com.example.MusicPlayer.service;

import com.example.MusicPlayer.repo.SongStatsRepo;
import org.springframework.stereotype.Service;

@Service
public class SongStatsService {
    private final SongStatsRepo songStatsRepo;

    public SongStatsService(SongStatsRepo songStatsRepo) {
        this.songStatsRepo = songStatsRepo;
    }
    public void updateViewsInSongStats(int songId){
        songStatsRepo.updateViewsInSongStats(1L,songId);
    }
    public void updateLikeAndDislikeInSongStats(int songId,long like,long dislike){
        songStatsRepo.updateLikeAndDislikeInSongStats(like,dislike,songId);
    }
}
