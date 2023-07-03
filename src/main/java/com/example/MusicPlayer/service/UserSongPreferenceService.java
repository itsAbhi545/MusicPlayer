package com.example.MusicPlayer.service;

import com.example.MusicPlayer.model.UserSongPreference;
import com.example.MusicPlayer.repo.UserSongPreferenceRepo;
import org.springframework.stereotype.Service;

@Service
public class UserSongPreferenceService {
    private final UserSongPreferenceRepo userSongPreferenceRepo;

    public UserSongPreferenceService(UserSongPreferenceRepo userSongPreferenceRepo) {
        this.userSongPreferenceRepo = userSongPreferenceRepo;
    }
    public UserSongPreference findUserPreference(long userId,int songId){
        return userSongPreferenceRepo.findUserPreference(userId,songId);
    }
    public void saveUserSongPreference(UserSongPreference userSongPreference){
        userSongPreferenceRepo.save(userSongPreference);
    }
}
