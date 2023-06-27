package com.example.MusicPlayer.model;

import jakarta.persistence.*;

@Entity
@Table(name="userwishlist")
public class UserWishlist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="songId")
    private Song song;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private Users user;

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Song getSong() {
        return song;
    }
    public UserWishlist song(Song song) {
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
    public UserWishlist user(Users user){
        this.user = user;
        return this;
    }
}
