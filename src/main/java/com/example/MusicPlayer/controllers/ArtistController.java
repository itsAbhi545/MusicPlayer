package com.example.MusicPlayer.controllers;

import com.example.MusicPlayer.model.Song;
import com.example.MusicPlayer.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {
    private final SongService songService;

    public ArtistController(SongService songService) {
        this.songService = songService;
    }
    @PostMapping("/upload/music")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadMusicByArtist(@RequestBody List<Song> songs){
        songService.saveSongsInDb(songs);
        System.out.println("Song start uploading!!!");
        return "Song uploaded Successfully!!!";
    }
    @DeleteMapping("/delete/music/{songId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSongByArtistOrAdmin(@PathVariable Integer songId){
        songService.deleteSongInDb(songId);
        return "Song Deleted Successfully!!!";
    }
}
