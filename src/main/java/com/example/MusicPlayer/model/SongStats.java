package com.example.MusicPlayer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "song_stats")
public class SongStats {
    @Id
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "songId")
    private Song song;
    private long likes;
    private long dislikes;
    private long views;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public void setDislikes(long dislikes) {
        this.dislikes = dislikes;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}
