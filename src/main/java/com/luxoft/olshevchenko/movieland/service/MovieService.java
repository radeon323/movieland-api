package com.luxoft.olshevchenko.movieland.service;

import com.luxoft.olshevchenko.movieland.entity.enums.Currencies;
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
    private final CurrencyService currencyService;

    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    public Movie getById(Long movieId, Currencies currency) {
        Movie movie = movieRepository.getById(movieId);

        if (Currencies.UAH != currency) {
            double rate = currencyService.getCurrencyRateOnThisDay(currency);
            movie.setPrice(Math.round(movie.getPrice()/rate));
            return movie;
        }
        return movie;
    }

    public List<Movie> getRandom(Long quantity) {
        return movieRepository.getRandom(quantity);
    }

    public List<Movie> sortByRating(String order) {
        return movieRepository.sortByRating(order);
    }

    public List<Movie> sortByPrice(String order) {
        return movieRepository.sortByPrice(order);
    }

    public List<Movie> getMoviesByGenreId(Long genreId) {
        return movieRepository.getByGenre(genreId);
    }
}
