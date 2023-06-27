package com.example.MusicPlayer.service;

import com.example.MusicPlayer.model.UserWishlist;
import com.example.MusicPlayer.repo.UserWishlistRepo;
import org.springframework.stereotype.Service;

@Service
public class UserWishlistService {
    private final UserWishlistRepo userWishlistRepo;

    public UserWishlistService(UserWishlistRepo userWishlistRepo) {
        this.userWishlistRepo = userWishlistRepo;
    }
    public void addSongInUserWishlist(UserWishlist userWishlist){
        userWishlistRepo.save(userWishlist);
    }
    public int removeSongFromWishlist(int userId,int wishlistId){
        System.out.println("dfdnaf");
        return userWishlistRepo.removeSongFromWishlist(userId,wishlistId);
    }
}
