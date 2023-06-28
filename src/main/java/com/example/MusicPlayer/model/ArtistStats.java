package com.example.MusicPlayer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity()
@Table(name = "artist_stats")
public class ArtistStats {
    @Id
    private long id;
    private long subscribers;
    private long uploads;
    private long views;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    @JsonIgnore
    private Users artist;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(long subscribers) {
        this.subscribers = subscribers;
    }

    public long getUploads() {
        return uploads;
    }

    public void setUploads(long uploads) {
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
