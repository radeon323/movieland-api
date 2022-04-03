package com.luxoft.olshevchenko.movieland.repository.jdbc;

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
public class MovieRowMapper implements RowMapper<Movie> {

    public Movie mapRow(ResultSet resultSet, int numRow) throws SQLException {

        Movie movie = mapRowMovie(resultSet);

        Set<Genre> genres = movie.getGenres();
        if (genres == null) {
            genres = new HashSet<>();
        }

        Genre genre = mapRowGenre(resultSet);
        genres.add(genre);

        movie.setGenres(genres);
        return movie;
    }


    private Movie mapRowMovie(ResultSet resultSet) throws SQLException {
        Long movieId = resultSet.getLong("movie_id");
        String movieName = resultSet.getString("movie_name");
        int year = resultSet.getInt("year");
        String country = resultSet.getString("country");
        String description = resultSet.getString("description");
        double price = resultSet.getDouble("price");
        double rating = resultSet.getDouble("rating");
        Movie movie = Movie.builder().
                movieId(movieId)
                .movieName(movieName)
                .year(year)
                .country(country)
                .description(description)
                .rating(rating)
                .price(price)
                .build();
        return movie;
    }

    private Genre mapRowGenre(ResultSet resultSet) throws SQLException {
        Long genreId = resultSet.getLong("genre_id");
        String genreName = resultSet.getString("genre");
        Genre genre = Genre.builder().
                genreId(genreId)
                .genre(genreName)
                .build();
        return genre;
    }

}
