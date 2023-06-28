package com.example.MusicPlayer.controllers;

import com.example.MusicPlayer.CustomExceptions.ApiException;
import com.example.MusicPlayer.dto.ApiResponse;
import com.example.MusicPlayer.model.*;
import com.example.MusicPlayer.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UsersService usersService;
    private final SongService songService;
    private final UserWishlistService userWishlistService;
    private final UserRoleService userRoleService;
    private final SubscribedArtistService subscribedArtistService;
    private final ArtistStatsService artistStatsService;
    @PostMapping("/create-user-account")
    public ApiResponse signup(@RequestBody Users user){
        return new ApiResponse("User successfully registered",usersService.register(user), HttpStatus.CREATED);
    }
    @PostMapping("/user/wishlist/{songId}")
    public ApiResponse userWishlist(@PathVariable Integer songId, Principal principal){

        boolean songExist =  songService.songExistInDb(songId);
        if(!songExist) throw new ApiException(HttpStatus.valueOf(400),"Song doesn't found!");

        Users user = usersService.getUserReferenceById(Integer.parseInt(principal.getName()));
        Song song = songService.getSongReferenceById(songId);

        UserWishlist userWishlist = userWishlistService.findUserWishlistBySongIdAndUserId(user.getId(),songId);
        if(userWishlist==null){
            userWishlist = new UserWishlist().song(song).user(user);
            userWishlistService.addSongInUserWishlist(userWishlist);
            return new ApiResponse("Song Added to wishlist Successfully!!",null,HttpStatus.OK);
        }
    return new ApiResponse("Song is already there in Wishlist!!",null,HttpStatus.OK);
    }
    //user -- artist ko subscribe kiya hoga

    @DeleteMapping("/user/wishlist/{wishlistId}")
    public ApiResponse removeSongFromUserWishlist(Principal principal,@PathVariable Integer wishlistId){
        userWishlistService.removeSongFromWishlist(Integer.parseInt(principal.getName()),wishlistId);
        return new ApiResponse("Song Removed from Wishlist Successfully!!",null,HttpStatus.OK);
    }

    @PostMapping("/subscribe/artist/{artistId}")
    public ApiResponse subscribeArtist(Principal principal,@PathVariable Integer artistId){
        UserRole userRole = userRoleService.findUserRoleByUserIdAndRoleId(artistId);
        if(userRole==null) throw new ApiException(HttpStatus.valueOf(400),"Can't subscribe the user!!");
        int userId = Integer.parseInt(principal.getName());
        SubscribedArtist subscribedArtist = subscribedArtistService.findByUserIdAndArtistId(userId,artistId);
        ApiResponse apiResponse = ApiResponse.builder().data(null).message("Artist Unsubscribed successfully!!").httpStatus(HttpStatus.ACCEPTED).build();
        if(subscribedArtist==null){
            Users user = usersService.getUserReferenceById(userId);
            artistStatsService.updateArtistSubscribersInArtistStats(artistId,1);
            subscribedArtistService.subscribeArtistWithId(new SubscribedArtist().artist(userRole.getUser()).user(user));
            apiResponse.setMessage("User successfully subscribed to the artist!!");
            return apiResponse;
        }
        artistStatsService.updateArtistSubscribersInArtistStats(artistId,-1);
        subscribedArtistService.unsubscribeArtistWithId(artistId);
        return apiResponse;
    }
}
