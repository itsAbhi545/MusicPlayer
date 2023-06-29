package com.example.MusicPlayer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "views")
public class Views {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }
    public Views song(Song song){
        this.song = song;
        return this;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    public Views user(Users user){
        this.user = user;
        return this;
    }
}
