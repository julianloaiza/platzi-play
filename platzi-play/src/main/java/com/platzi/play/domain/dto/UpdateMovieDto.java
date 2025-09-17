package com.platzi.play.domain.dto;

import com.platzi.play.domain.enums.Genre;
import jakarta.validation.constraints.*;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public record UpdateMovieDto(
        @NotNull @NotBlank(message = "The title is required")
        String title,
        @PastOrPresent(message = "The date needs to be now or in the past")
        LocalDate releaseDate,
        @Nullable
        @DecimalMin(value = "0.0", message = "The rating needs to be greater than or equal to 0")
        @DecimalMax(value = "5.0", message = "The rating needs to be less than or equal to 5")
        Double rating
) {
}
