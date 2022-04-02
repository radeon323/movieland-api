package com.luxoft.olshevchenko.movieland.repository.jdbc;

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
public class MovieResultSetExtractor implements ResultSetExtractor <Movie> {

    @Override
    public Movie extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return null;
        }

        Movie movie = mapRowMovie(resultSet);

        Set<Genre> genres = movie.getGenres();
        if(genres == null){
            genres = new HashSet<>();
        }

        genres.add(mapRowGenre(resultSet));

        while (resultSet.next()) {
            genres.add(mapRowGenre(resultSet));
        }

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
                movie_id(movieId)
                .movie_name(movieName)
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
                id(genreId)
                .genre(genreName)
                .build();
        return genre;
    }
}
