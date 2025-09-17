package com.platzi.play.web.controller;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.domain.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name="movies", description = "Operations about movie of Platzi Play")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Optional: Swagger automatically generates documentation, but these annotations allow customization
    @Operation(summary = "Get all movies", description = "Retrieves a list of all available movies")
    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll() {
        List<MovieDto> movies = this.movieService.getAll();
        if (movies.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movies);
    }

    // Complete example: This shows how to fully document an endpoint with all possible responses, parameters, and constraints
    @Operation(
        summary = "Get movie by ID",
        description = "Retrieves a specific movie by its unique identifier. Returns detailed movie information including title, duration, genre, release date, rating, and state."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Movie found successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MovieDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Movie not found",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid ID format",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(
        @Parameter(
            description = "Unique identifier of the movie to retrieve",
            required = true,
            example = "1"
        )
        @PathVariable long id
    ) {
        MovieDto movieDto = this.movieService.getById(id);
        if (movieDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(movieDto);
    }

    @Operation(summary = "Create a new movie", description = "Adds a new movie to the system")
    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody @Valid MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    @Operation(summary = "Update movie", description = "Updates an existing movie with new information")
    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable long id, @RequestBody @Valid UpdateMovieDto updateMovieDto) {
        MovieDto updatedMovie = this.movieService.update(id, updateMovieDto);
        if (updatedMovie == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedMovie);
    }

    @Operation(summary = "Delete movie", description = "Removes a movie from the system")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        boolean deleted = this.movieService.deleteById(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
