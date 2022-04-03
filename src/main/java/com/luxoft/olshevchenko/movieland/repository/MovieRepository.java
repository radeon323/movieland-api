package com.luxoft.olshevchenko.movieland.repository;

import com.luxoft.olshevchenko.movieland.entity.Movie;

import java.util.List;

/**
 * @author Oleksandr Shevchenko
 */
public interface MovieRepository {
    public List<Movie> getAll();

    public Movie getById(Long id);

    public List<Movie> getRandom(Long quantity);

    public void add(Movie movie);

    public void remove(int id);

    public void edit(Movie movie);


}
