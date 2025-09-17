package com.platzi.play.domain.dto;

import com.platzi.play.domain.enums.Genre;
import org.springframework.lang.Nullable;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record MovieDto(
        Long id,
        @NotNull @NotBlank(message = "The title is required")
        @Size(max = 150, message = "The title must not exceed 150 characters")
        String title,
        @NotNull(message = "The duration is required")
        @Min(value = 1, message = "The duration must be at least 1 minute")
        @Max(value = 999, message = "The duration must not exceed 999 minutes")
        Integer duration,
        @NotNull(message = "The genre is required")
        Genre genre,
        @PastOrPresent(message = "The date needs to be now or in the past")
        LocalDate releaseDate,
        @Nullable
        @DecimalMin(value = "0.0", message = "The rating needs to be greater than or equal to 0")
        @DecimalMax(value = "5.0", message = "The rating needs to be less than or equal to 5")
        Double rating,
        @NotNull(message = "The state is required")
        Boolean state
) {
}
