package com.example.MusicPlayer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArtistListDto {
    private String firstName;
    private String lastName;
    private String songImageUrl;

    public ArtistListDto(String firstName, String lastName, String songImageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.songImageUrl = songImageUrl;
    }
}
