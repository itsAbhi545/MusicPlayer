package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepo extends JpaRepository<Song,Integer> {
}
