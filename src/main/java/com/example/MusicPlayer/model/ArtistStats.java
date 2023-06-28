package com.example.MusicPlayer.model;

import jakarta.persistence.*;

@Table
@Entity(name = "artiststats")
public class ArtistStats {
    @Id
    private long id;
    private int subscribers;
    private int uploads;
    private long views;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Users artist;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public int getUploads() {
        return uploads;
    }

    public void setUploads(int uploads) {
        this.uploads = uploads;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public Users getArtist() {
        return artist;
    }

    public void setArtist(Users artist) {
        this.artist = artist;
    }
}
