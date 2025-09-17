package com.platzi.play.domain.dto;

import com.platzi.play.domain.enums.Genre;

import java.time.LocalDate;

public record MovieDto(
        String title,
        Integer duration,
        Genre genre,
        LocalDate releaseDate,
        Double rating,
        Boolean state
) {
}
