package com.example.MusicPlayer.controllers;

import com.example.MusicPlayer.dto.ArtistListDto;
import com.example.MusicPlayer.dto.SongDto;
import com.example.MusicPlayer.model.ArtistStats;
import com.example.MusicPlayer.model.Song;
import com.example.MusicPlayer.model.Views;
import com.example.MusicPlayer.service.*;
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
    private final SubscribedArtistService subscribedArtistService;
    private final ViewsService viewsService;
    private final SongStatsService songStatsService;

    public ArtistController(SongService songService, UsersService usersService, ArtistStatsService artistStatsService, SubscribedArtistService subscribedArtistService, ViewsService viewsService, SongStatsService songStatsService) {
        this.songService = songService;
        this.usersService = usersService;
        this.artistStatsService = artistStatsService;
        this.subscribedArtistService = subscribedArtistService;
        this.viewsService = viewsService;
        this.songStatsService = songStatsService;
    }

    @PostMapping("/upload/music")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadMusicByArtist(@RequestBody List<Song> songs,Principal principal){
        long artistId = Long.parseLong(principal.getName());
        songService.saveSongsInDb(songs);
        artistStatsService.updateArtistUploadsInArtistStats(artistId,songs.size());
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
        return subscribedArtistService.getSubscribedArtistList(userId);//subscribe -- artist// views -- artist
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
    //save the song view
    @GetMapping("/music/{musicId}")
    public SongDto getMusicByMusicId(Principal principal,@PathVariable Integer musicId){
       long userId = Long.parseLong(principal.getName());
       SongDto song = songService.getSongBySongId(musicId);
       int view_count = viewsService.viewExistByUserIdAndSongId(userId,musicId);
       if(view_count==0 && song!=null){
           //saving the  view in db
           viewsService.saveViewInDB(new Views()
                   .song(songService.getSongReferenceById(musicId))
                   .user(usersService.getUserReferenceById(userId)));
           //updating the view of the song in the song stats!!
           songStatsService.updateViewsInSongStats(musicId);
           //updating the view of the song in the artist stats!!
           artistStatsService.updateViewsInArtistStats(song.artistId());
       }
       return song;
    }
    @GetMapping("/artist/statistics")
    public ArtistStats getArtistStatistics(Principal principal){
        long userId = Long.parseLong(principal.getName());
        return artistStatsService.getArtistStatsById(userId);
    }
}
