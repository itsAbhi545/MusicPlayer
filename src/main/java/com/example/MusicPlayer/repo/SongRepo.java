package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SongRepo extends JpaRepository<Song,Integer> {
//    @Query(value = "insert into song values(119,'S','p','y','y','r')",nativeQuery = true)
//    List<Song> saveSong();
    @Transactional @Modifying
    @Query("update Song song set song.softDelete = ?2 where song.songId = ?1")
    void deleteSongInDb(int songId,boolean flag);
    @Query("Select song.songName from Song song where song.artist.id = ?1")
    List<String> getSongByArtistId(long artistId);
}
