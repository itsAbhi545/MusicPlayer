package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.dto.SongDto;
import com.example.MusicPlayer.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SongRepo extends JpaRepository<Song,Integer> {
    @Transactional @Modifying
    @Query("update Song song set song.softDelete = ?2 where song.songId = ?1")
    void deleteSongInDb(int songId,boolean flag);
    @Query("Select song.songName from Song song where song.artist.id = ?1")
    List<String> getSongByArtistId(long artistId);
    //SongDto
    @Query("Select new com.example.MusicPlayer.dto.SongDto(song.songName,artist.firstName,artist.lastName,song.duration,song.released,song.songImageUrl,song.songAudioUrl) from Song song inner join song.artist artist where song.songId = ?1")
    SongDto getSongBySongId(int songId);
}
