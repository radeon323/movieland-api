package com.luxoft.olshevchenko.movieland.repository;

import com.luxoft.olshevchenko.movieland.entity.Genre;

import java.util.List;

/**
 * @author Oleksandr Shevchenko
 */
public interface GenreRepository {
    List<Genre> getAll();
}
