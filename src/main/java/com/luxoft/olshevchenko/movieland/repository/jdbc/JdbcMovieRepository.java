package com.luxoft.olshevchenko.movieland.repository.jdbc;

import com.luxoft.olshevchenko.movieland.repository.MovieRepository;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import com.luxoft.olshevchenko.movieland.repository.mapper.MovieResultSetExtractor;
import com.luxoft.olshevchenko.movieland.repository.mapper.MovieListResultSetExtractor;
import com.luxoft.olshevchenko.movieland.repository.mapper.MovieRowMapper;
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
    private static final MovieListResultSetExtractor MOVIE_LIST_RESULT_SET_EXTRACTOR = new MovieListResultSetExtractor();
    private static final MovieResultSetExtractor MOVIE_RESULT_SET_EXTRACTOR = new MovieResultSetExtractor();
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    private static final String FIND_ALL = "SELECT movie_id, movie_name, year, country, description, price, rating FROM movie;";
    private static final String FIND_BY_ID = "SELECT movie_id, movie_name, year, country, description, price, rating FROM movie WHERE movie_id = ?";
    private static final String FIND_ALL_WITH_GENRES =
            "SELECT movie.movie_id, movie.movie_name, movie.year, movie.country, " +
            "movie.description, movie.price, movie.rating, genres.genre_id, genres.genre FROM movie " +
            "LEFT JOIN movie_genres " +
            "ON movie.movie_id = movie_genres.movie_id " +
            "LEFT JOIN genres " +
            "ON genres.genre_id = movie_genres.genre_id ";
    private static final String FIND_BY_ID_WITH_GENRES = FIND_ALL_WITH_GENRES + "WHERE movie.movie_id = ?;";
    private static final String ADD = "INSERT INTO movie (movie_name, year, country, description, price, rating) VALUES (:name, :year, :country, :description, :price);";
    private static final String DELETE_BY_ID = "DELETE FROM movie WHERE movie_id = ?;";
    private static final String UPDATE_BY_ID = "UPDATE movie SET movie_name = ?, year = ?, country = ?, description = ?, price = ?, rating = ? WHERE movie_id = ?;";
    private static final String SELECT_RANDOM = FIND_ALL_WITH_GENRES + "ORDER BY RANDOM() LIMIT ?;";
    private static final String SORT_BY_RATING = FIND_ALL_WITH_GENRES + "ORDER BY movie.rating ";
    private static final String GET_BY_GENRE_ID = FIND_ALL_WITH_GENRES + "WHERE genres.genre_id = ?;";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = jdbcTemplate.query(FIND_ALL_WITH_GENRES, MOVIE_LIST_RESULT_SET_EXTRACTOR);
        return movies;
    }

    @Override
    public void add(Movie movie) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", movie.getMovieName());
        parameters.put("year", movie.getYear());
        parameters.put("country", movie.getCountry());
        parameters.put("description", movie.getDescription());
        parameters.put("price", movie.getPrice());
        parameters.put("rating", movie.getRating());
        namedParameterJdbcTemplate.update(ADD, parameters);
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public void edit(Movie movie) {
        jdbcTemplate.update(UPDATE_BY_ID, movie.getMovieName(), movie.getYear(), movie.getCountry(), movie.getDescription(), movie.getRating(), movie.getPrice(), movie.getMovieId());
    }

    @Override
    public List<Movie> sortByRating(String order) {
        StringJoiner joiner = new StringJoiner("",SORT_BY_RATING,order);
        List<Movie> movies = jdbcTemplate.query(joiner.toString(), MOVIE_LIST_RESULT_SET_EXTRACTOR);
        return movies;
    }

    @Override
    public List<Movie> getByGenre(Long genreId) {
        List<Movie> movies = jdbcTemplate.query(GET_BY_GENRE_ID, MOVIE_LIST_RESULT_SET_EXTRACTOR, genreId);
        return movies;
    }

    @Override
    public Movie getById(Long movieId) {
        return jdbcTemplate.query(FIND_BY_ID_WITH_GENRES, MOVIE_RESULT_SET_EXTRACTOR, movieId);
    }

    @Override
    public List<Movie> getRandom(Long quantity) {
        List<Movie> movies = jdbcTemplate.query(SELECT_RANDOM, MOVIE_LIST_RESULT_SET_EXTRACTOR, quantity);
        return movies;
    }

}
