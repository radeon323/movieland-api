package com.luxoft.olshevchenko.movieland.repository.mapper;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleksandr Shevchenko
 */
public class MovieFullRowMapper implements RowMapper<Movie> {
    MovieRowMapper movieRowMapper = new MovieRowMapper();
    GenreRowMapper genreRowMapper = new GenreRowMapper();

    public Movie mapRow(ResultSet resultSet, int numRow) throws SQLException {
        Movie movie = movieRowMapper.mapRow(resultSet, numRow);

        Set<Genre> genres = movie.getGenres();
        if (genres == null) {
            genres = new HashSet<>();
        }

        Genre genre = genreRowMapper.mapRow(resultSet, numRow);
        genres.add(genre);

        movie.setGenres(genres);
        return movie;
    }
}
