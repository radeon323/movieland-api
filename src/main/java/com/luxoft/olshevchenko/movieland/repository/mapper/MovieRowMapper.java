package com.luxoft.olshevchenko.movieland.repository.mapper;

import com.luxoft.olshevchenko.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Oleksandr Shevchenko
 */
public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
}
