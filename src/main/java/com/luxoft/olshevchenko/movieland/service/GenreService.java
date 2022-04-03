package com.luxoft.olshevchenko.movieland.service;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oleksandr Shevchenko
 */
@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> getAll() {
        return genreRepository.getAll();
    }
}
