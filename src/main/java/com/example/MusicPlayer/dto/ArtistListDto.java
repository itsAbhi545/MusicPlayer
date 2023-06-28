package com.example.MusicPlayer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArtistListDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String songImageUrl;

    public ArtistListDto(Long id,String firstName, String lastName, String songImageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.songImageUrl = songImageUrl;
    }
}
