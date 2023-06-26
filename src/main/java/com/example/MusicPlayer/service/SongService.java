package com.example.MusicPlayer.service;

import com.example.MusicPlayer.model.Song;
import com.example.MusicPlayer.repo.SongRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepo songRepo;

    public SongService(SongRepo songRepo) {
        this.songRepo = songRepo;
    }
    public List<Song> saveSongsInDb(List<Song> songs){
        return songRepo.saveAll(songs);
    }
    public void deleteSongInDb(int songId){
        songRepo.deleteSongInDb(songId,true);
    }
}
