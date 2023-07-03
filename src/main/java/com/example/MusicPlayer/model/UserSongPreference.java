package com.example.MusicPlayer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_song_preference")
public class UserSongPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    @Column(name = "liked")
    private boolean liked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    public UserSongPreference user(Users users){
        this.user = users;
        return this;
    }
    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
    public UserSongPreference song(Song song){
        this.song = song;
        return this;
    }
    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
    public UserSongPreference liked(boolean liked){
        this.liked = liked;
        return  this;
    }
}
