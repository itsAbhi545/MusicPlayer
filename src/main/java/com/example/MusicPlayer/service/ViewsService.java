package com.example.MusicPlayer.service;

import com.example.MusicPlayer.model.Views;
import com.example.MusicPlayer.repo.ViewsRepo;
import org.springframework.stereotype.Service;

@Service
public class ViewsService {
    private final ViewsRepo viewsRepo;

    public ViewsService(ViewsRepo viewsRepo) {
        this.viewsRepo = viewsRepo;
    }
    //check user viewed already or not!!!
    public int viewExistByUserIdAndSongId(long userId,int songId){
        return viewsRepo.viewExistByUserIdAndSongId(userId,songId);
    }
    public Views saveViewInDB(Views songView){
        return viewsRepo.save(songView);
    }
}
