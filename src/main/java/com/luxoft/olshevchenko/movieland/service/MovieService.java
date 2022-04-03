package com.luxoft.olshevchenko.movieland.service;

import com.luxoft.olshevchenko.movieland.repository.MovieRepository;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Movie getById(Long movieId, String currency) {
        Movie movie = movieRepository.getById(movieId, currency);

        if (Objects.equals(currency, "USD") || Objects.equals(currency, "usd") || Objects.equals(currency, "EUR") || Objects.equals(currency, "eur")) {
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

    public List<Movie> getMoviesByGenreId(Long genreId) {
        return movieRepository.getByGenre(genreId);
    }
}
