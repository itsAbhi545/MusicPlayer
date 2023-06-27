package com.example.MusicPlayer.model;

import jakarta.persistence.*;

@Entity
@Table(name="subscribedArtist")
public class SubscribedArtist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artistId")
    private Users artist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    public SubscribedArtist user(Users user){
        this.user = user;
        return this;
    }
    public Users getArtist() {
        return artist;
    }

    public void setArtist(Users artist) {
        this.artist = artist;
    }
    public SubscribedArtist artist(Users artist){
        this.artist = artist;
        return this;
    }
}
