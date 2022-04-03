package com.luxoft.olshevchenko.movieland.repository.mapper;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleksandr Shevchenko
 */
public class MovieResultSetExtractor implements ResultSetExtractor<Movie> {
    MovieRowMapper movieRowMapper = new MovieRowMapper();
    GenreRowMapper genreRowMapper = new GenreRowMapper();
    int numRow = 0;

    @Override
    public Movie extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return null;
        }

        Movie movie = movieRowMapper.mapRow(resultSet, numRow);

        Set<Genre> genres = movie.getGenres();
        if(genres == null){
            genres = new HashSet<>();
        }

        genres.add(genreRowMapper.mapRow(resultSet, numRow));

        while (resultSet.next()) {
            genres.add(genreRowMapper.mapRow(resultSet, numRow));
        }

        movie.setGenres(genres);

        return movie;
    }
}
