package com.luxoft.olshevchenko.movieland.repository.jdbc;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.repository.MovieRepository;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * @author Oleksandr Shevchenko
 */
@Repository
@RequiredArgsConstructor
public class JdbcMovieRepository implements MovieRepository {
    Logger logger = LoggerFactory.getLogger(getClass());
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private static final MovieResultSetExtractor MOVIE_RESULT_SET_EXTRACTOR = new MovieResultSetExtractor();
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String FIND_ALL_SQL = "SELECT movie_id, movie_name, year, country, description, price, rating FROM movie;";
    private static final String FIND_ALL_WITH_GENRES_SQL =
            "SELECT * FROM movie " +
            "LEFT JOIN movie_genres " +
            "ON movie.movie_id = movie_genres.movie_id " +
            "LEFT JOIN genres " +
            "ON genres.genre_id = movie_genres.genre_id;";

    private static final String FIND_BY_ID = "SELECT movie_id, movie_name, year, country, description, price, rating FROM movie WHERE movie_id = ?";
    private static final String FIND_BY_ID_WITH_GENRES_SQL =
            "SELECT * FROM movie " +
            "LEFT JOIN movie_genres " +
            "ON movie.movie_id = movie_genres.movie_id " +
            "LEFT JOIN genres " +
            "ON genres.genre_id = movie_genres.genre_id " +
            "WHERE movie.movie_id = ?;";

    private static final String ADD = "INSERT INTO movie (name, year, country, description, price) VALUES (:name, :year, :country, :description, :price);";

    private static final String DELETE_BY_ID = "DELETE FROM movie WHERE movie_id = ?;";
    private static final String UPDATE_BY_ID = "UPDATE movie SET movie_name = ?, year = ?, country = ?, description = ?, price = ?, rating = ? WHERE movie_id = ?;";

    @Override
    public List<Movie> getAll() {
        List<Movie> getAll = jdbcTemplate.query(FIND_ALL_WITH_GENRES_SQL, MOVIE_ROW_MAPPER);
        return getAll;
    }


    @Override
    public void add(Movie movie) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", movie.getMovie_name());
        parameters.put("year", movie.getYear());
        parameters.put("country", movie.getCountry());
        parameters.put("description", movie.getDescription());
        parameters.put("price", movie.getPrice());
        namedParameterJdbcTemplate.update(ADD, parameters);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public void edit(Movie movie) {
        jdbcTemplate.update(UPDATE_BY_ID, movie.getMovie_name(), movie.getYear(), movie.getCountry(), movie.getDescription(), movie.getRating(), movie.getPrice(), movie.getMovie_id());
    }

    @Override
    public Movie getById(Long id) {
        return jdbcTemplate.query(FIND_BY_ID_WITH_GENRES_SQL, MOVIE_RESULT_SET_EXTRACTOR, id);
    }

}
