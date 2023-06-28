package com.example.MusicPlayer.controllers;

import com.example.MusicPlayer.dto.ArtistListDto;
import com.example.MusicPlayer.model.Song;
import com.example.MusicPlayer.service.SongService;
import com.example.MusicPlayer.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {
    private final SongService songService;
    private final UsersService usersService;

    public ArtistController(SongService songService, UsersService usersService) {
        this.songService = songService;
        this.usersService = usersService;
    }

    @PostMapping("/upload/music")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadMusicByArtist(@RequestBody List<Song> songs){
        songService.saveSongsInDb(songs);
        System.out.println("Song start uploading!!!");
        return "Song uploaded Successfully!!!";
    }
    @GetMapping("/artist/list")//artist image //artist name
    public List<ArtistListDto> getArtistList(){
        return usersService.getArtistList();
    }
    @DeleteMapping("/delete/music/{songId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSongByArtistOrAdmin(@PathVariable Integer songId){
        songService.deleteSongInDb(songId);
        return "Song Deleted Successfully!!!";
    }
    @GetMapping("/music/artist/{artistId}")
    public List<String> getMusicOfArtistWithId(@PathVariable Long artistId){
        return songService.getSongByArtistId(artistId);
    }
}
