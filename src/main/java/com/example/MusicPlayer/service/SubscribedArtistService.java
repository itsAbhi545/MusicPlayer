package com.example.MusicPlayer.service;

import com.example.MusicPlayer.dto.ArtistListDto;
import com.example.MusicPlayer.model.SubscribedArtist;
import com.example.MusicPlayer.repo.SubscribedArtistRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribedArtistService {
    private final SubscribedArtistRepo subscribedArtistRepo;

    public SubscribedArtistService(SubscribedArtistRepo subscribedArtistRepo) {
        this.subscribedArtistRepo = subscribedArtistRepo;
    }
    public SubscribedArtist findByUserIdAndArtistId(long userId,long artistId){
         return subscribedArtistRepo.findByUserIdAndArtistId(userId,artistId);
    }
    public SubscribedArtist subscribeArtistWithId(SubscribedArtist subscribedArtist){
        return subscribedArtistRepo.save(subscribedArtist);
    }
    public int unsubscribeArtistWithId(int artistId){
        return subscribedArtistRepo.unsubscribeArtistWithId(artistId);
    }
    public List<ArtistListDto> getSubscribedArtistList(long userId){
        return subscribedArtistRepo.getSubscribedArtistList(userId);
    }
}
