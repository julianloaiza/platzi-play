package com.platzi.play.persistence.mapper;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, StateMapper.class})
public interface MovieMapper {

    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "duracion", target = "duration")
    @Mapping(source = "genero", target = "genre", qualifiedByName = "stringToGenre")
    @Mapping(source = "fechaEstreno", target = "releaseDate")
    @Mapping(source = "clasificacion", target = "rating")
    @Mapping(source =  "estado", target = "state", qualifiedByName = "charToBoolean")
    MovieDto toDto(MovieEntity entity);
    List<MovieDto> toDto(Iterable<MovieEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "genre", target = "genero", qualifiedByName = "genreToString")
    @Mapping(source =  "state", target = "estado", qualifiedByName = "booleanToChar")
    MovieEntity toEntity(MovieDto dto);
}