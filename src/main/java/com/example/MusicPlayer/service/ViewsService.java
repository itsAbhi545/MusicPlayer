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
    int viewExistByUserIdAndSongId(int userId,int songId){
        return viewsRepo.viewExistByUserIdAndSongId(userId,songId);
    }
    Views saveViewInDB(Views songView){
        return viewsRepo.save(songView);
    }
}
