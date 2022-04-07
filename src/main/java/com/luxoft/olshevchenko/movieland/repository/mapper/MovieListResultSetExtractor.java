package com.luxoft.olshevchenko.movieland.repository.mapper;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Oleksandr Shevchenko
 */
public class MovieListResultSetExtractor implements ResultSetExtractor<List<Movie>> {
    MovieRowMapper movieRowMapper = new MovieRowMapper();
    GenreRowMapper genreRowMapper = new GenreRowMapper();
    int numRow = 0;


    @Override
    public List<Movie> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Movie> movies = new ArrayList<>();
        while (resultSet.next()) {
            Movie movie = movieRowMapper.mapRow(resultSet, numRow);
            Set<Genre> genres = movie.getGenres();
            if (genres == null) {
                genres = new HashSet<>();
            }
            Genre genre = genreRowMapper.mapRow(resultSet, numRow);
            genres.add(genre);
            movie.setGenres(genres);
            movies.add(movie);
        }
        addAllGenres(movies);
        return movies;
    }

    private void addAllGenres(List<Movie> movies) {
        movies.sort(Comparator.comparingLong(Movie::getMovieId));
        for (int i = 0; i < movies.size()-1; i++) {
            if(Objects.equals(movies.get(i).getMovieId(), movies.get(i + 1).getMovieId())) {
                movies.get(i).getGenres().addAll(movies.get(i+1).getGenres());
                movies.remove(i+1);
            }
        }
    }


}

