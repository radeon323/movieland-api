package com.luxoft.olshevchenko.movieland.repository;

import com.luxoft.olshevchenko.movieland.entity.Movie;

import java.util.List;

/**
 * @author Oleksandr Shevchenko
 */
public interface MovieRepository {
    List<Movie> getAll();

    Movie getById(Long movieId);

    List<Movie> getRandom(Long quantity);

    void add(Movie movie);

    void remove(Long id);

    void edit(Movie movie);

    List<Movie> sortByRating(String order);

    List<Movie> sortByPrice(String order);

    List<Movie> getByGenre(Long genreId);
}
