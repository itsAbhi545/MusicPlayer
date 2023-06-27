package com.example.MusicPlayer.controller;

import com.example.MusicPlayer.CustomExceptions.ApiException;
import com.example.MusicPlayer.dto.ApiResponse;
import com.example.MusicPlayer.model.Song;
import com.example.MusicPlayer.model.UserWishlist;
import com.example.MusicPlayer.model.Users;
import com.example.MusicPlayer.service.SongService;
import com.example.MusicPlayer.service.UserWishlistService;
import com.example.MusicPlayer.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {
    private final UsersService usersService;
    private final SongService songService;
    private final UserWishlistService userWishlistService;
    @PostMapping("/create-user-account")
    public ApiResponse signup(@RequestBody Users user){
        return new ApiResponse("User successfully registered",usersService.register(user), HttpStatus.CREATED);
    }
    //like -- dislike
    //wishlist
    @PostMapping("/user/wishlist/{songId}")
    public ApiResponse userWishlist(@PathVariable Integer songId, Principal principal){
        //add to wishlist]
        //exist
        boolean songExist =  songService.songExistInDb(songId);
        if(!songExist) throw new ApiException(HttpStatus.valueOf(400),"Song doesn't found!");
        Users user = usersService.getUserReferenceById(Integer.parseInt(principal.getName()));
        Song song = songService.getSongReferenceById(songId);
        UserWishlist userWishlist = new UserWishlist().song(song).user(user);
        userWishlistService.addSongInUserWishlist(userWishlist);
        return new ApiResponse("Song Added to wishlist Successfully!!",null,HttpStatus.OK);
    }
    @DeleteMapping("/user/wishlist/{wishlistId}")
    public ApiResponse removeSongFromUserWishlist(Principal principal,@PathVariable Integer wishlistId){
        //first we have to check if it exist in the data
        userWishlistService.removeSongFromWishlist(Integer.parseInt(principal.getName()),wishlistId);
        return new ApiResponse("Song Removed from Wishlist Successfully!!",null,HttpStatus.OK);
    }
}
