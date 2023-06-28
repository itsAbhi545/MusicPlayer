package com.example.MusicPlayer.controllers;

import com.example.MusicPlayer.dto.ArtistListDto;
import com.example.MusicPlayer.dto.SongDto;
import com.example.MusicPlayer.model.ArtistStats;
import com.example.MusicPlayer.model.Song;
import com.example.MusicPlayer.service.ArtistStatsService;
import com.example.MusicPlayer.service.SongService;
import com.example.MusicPlayer.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {
    private final SongService songService;
    private final UsersService usersService;
    private final ArtistStatsService artistStatsService;

    public ArtistController(SongService songService, UsersService usersService, ArtistStatsService artistStatsService) {
        this.songService = songService;
        this.usersService = usersService;
        this.artistStatsService = artistStatsService;
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
    @GetMapping("/subscribed-artist/list")
    public List<ArtistListDto> subscribeArtistList(Principal principal){
        long userId = Long.parseLong(principal.getName());
        return null;//subscribe -- artist// views -- artist
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
    @GetMapping("/music/{musicId}")
    public SongDto getMusicByMusicId(@PathVariable Integer musicId){
       return songService.getSongBySongId(musicId);
    }
    @GetMapping("/artist/statistics")
    public ArtistStats getArtistStatistics(Principal principal){
        long userId = Long.parseLong(principal.getName());
        return artistStatsService.getArtistStatsById(userId);
    }
}
