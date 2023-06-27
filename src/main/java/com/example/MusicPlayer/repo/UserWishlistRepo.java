package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.UserWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserWishlistRepo extends JpaRepository<UserWishlist,Integer> {
    @Transactional @Modifying
    @Query("delete from UserWishlist uw where uw.user.id = ?1 AND uw.wishlistId = ?2")
    int removeSongFromWishlist(long userId,int wishlistId);
    @Query("Select uw from UserWishlist uw where uw.user.id = ?1 AND uw.song.songId = ?2")
    UserWishlist findUserWishlistBySongIdAndUserId(long userId,int songId);
}
