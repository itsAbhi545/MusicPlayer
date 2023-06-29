package com.example.MusicPlayer.repo;

import com.example.MusicPlayer.model.Views;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewsRepo extends JpaRepository<Views,Long> {
    @Query(value = "Select count(*) from views where views.user_id = ?1 AND views.song_id = ?2",nativeQuery = true)
    int viewExistByUserIdAndSongId(int userId, int songId);

}
