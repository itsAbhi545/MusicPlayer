package com.example.MusicPlayer.service;

import com.example.MusicPlayer.model.Song;
import com.example.MusicPlayer.repo.SongRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public boolean songExistInDb(int songId){
        return songRepo.existsById(songId);
    }
    public Song getSongReferenceById(int songId){
        return songRepo.getReferenceById(songId);
    }
    public Optional<Song> findSongById(int songId){
        return songRepo.findById(songId);
    }
}
