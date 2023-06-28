package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.ArtistStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistStatsRepo extends JpaRepository<ArtistStats,Long> {

}
