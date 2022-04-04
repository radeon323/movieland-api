package com.luxoft.olshevchenko.movieland.repository;

import com.luxoft.olshevchenko.movieland.entity.Movie;

import java.util.List;

/**
 * @author Oleksandr Shevchenko
 */
public interface MovieRepository {
    public List<Movie> getAll();

    public Movie getById(Long movieId);

    public List<Movie> getRandom(Long quantity);

    public void add(Movie movie);

    public void remove(Long id);

    public void edit(Movie movie);

    public List<Movie> sortByRating(String order);

    List<Movie> getByGenre(Long genreId);
}
