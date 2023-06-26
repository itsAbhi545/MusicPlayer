package com.example.MusicPlayer.model;

import jakarta.persistence.*;

@Entity
@Table(name="song")
public class Song {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SongId;
    private String songName;
    private String artistName;
    private String duration;
    private String songImageUrl;
    private String songAudioUrl;

    public int getSongId() {
        return SongId;
    }

    public void setSongId(int songId) {
        SongId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSongImageUrl() {
        return songImageUrl;
    }

    public void setSongImageUrl(String songImageUrl) {
        this.songImageUrl = songImageUrl;
    }

    public String getSongAudioUrl() {
        return songAudioUrl;
    }

    public void setSongAudioUrl(String songAudioUrl) {
        this.songAudioUrl = songAudioUrl;
    }
}
