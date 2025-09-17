package com.platzi.play.domain.service;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.domain.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> getAll() {
        return movieRepository.getAll();
    }

    public MovieDto getById(long id) {
        return movieRepository.getById(id);
    }

    public MovieDto add(MovieDto movieDto) {
        return movieRepository.save(movieDto);
    }

    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {
        return movieRepository.update(id, updateMovieDto);
    }

    public boolean deleteById(long id) {
        return movieRepository.deleteById(id);
    }

}
