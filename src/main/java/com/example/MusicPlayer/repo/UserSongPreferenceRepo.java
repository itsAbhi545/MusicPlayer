package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.UserSongPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserSongPreferenceRepo extends JpaRepository<UserSongPreference,Integer> {
    @Query("Select up from UserSongPreference up where up.user.id = ?1 AND up.song.songId = ?2")
    UserSongPreference findUserPreference(long userId,int songId);

}
