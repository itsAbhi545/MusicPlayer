package com.example.MusicPlayer.service;

import com.example.MusicPlayer.model.ArtistStats;
import com.example.MusicPlayer.repo.ArtistStatsRepo;
import org.springframework.stereotype.Service;

@Service
public class ArtistStatsService {
    private final ArtistStatsRepo artistStatsRepo;

    public ArtistStatsService(ArtistStatsRepo artistStatsRepo) {
        this.artistStatsRepo = artistStatsRepo;
    }
    public ArtistStats getArtistStatsById(long artistId){
        return artistStatsRepo.findById(artistId).orElse(null);
    }
    public void updateArtistSubscribersInArtistStats(long artistId,long subscribers){
        artistStatsRepo.updateArtistSubscribersInArtistStats(subscribers,artistId);
    }
}
