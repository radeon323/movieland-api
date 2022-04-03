package com.luxoft.olshevchenko.movieland.service;

import com.luxoft.olshevchenko.movieland.repository.MovieRepository;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oleksandr Shevchenko
 */
@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    public Movie getById(Long id) {
        return movieRepository.getById(id);
    }

    public List<Movie> getRandom(Long quantity) {
        return movieRepository.getRandom(quantity);
    }


    public List<Movie> sortByRating(String order) {
        return movieRepository.sortByRating(order);
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }
}
