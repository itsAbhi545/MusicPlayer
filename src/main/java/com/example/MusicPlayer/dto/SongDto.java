package com.example.MusicPlayer.dto;

public class SongDto {
    private String songName;
    private String artistName;
    private String duration;
    private String released;
    private String songImageUrl;
    private String songAudioUrl;

    public SongDto(String songName,String firstName,String lastName, String duration, String released, String songImageUrl, String songAudioUrl) {
        this.songName = songName;
        this.artistName = firstName + " " + lastName;
        this.duration = duration;
        this.released = released;
        this.songImageUrl = songImageUrl;
        this.songAudioUrl = songAudioUrl;
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

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
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
