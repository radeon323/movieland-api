package com.luxoft.olshevchenko.movieland.repository.mapper;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleksandr Shevchenko
 */
public class GenreRowMapper implements RowMapper<Genre> {
    public Genre mapRow(ResultSet resultSet, int numRow) throws SQLException {
        Long genreId = resultSet.getLong("genre_id");
        String genreName = resultSet.getString("genre");
        Genre genre = Genre.builder().
                genreId(genreId)
                .genre(genreName)
                .build();
        return genre;
    }
}
