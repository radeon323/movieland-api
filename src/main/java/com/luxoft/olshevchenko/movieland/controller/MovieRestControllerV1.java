package com.luxoft.olshevchenko.movieland.controller;

import com.luxoft.olshevchenko.movieland.entity.enums.Order;
import com.luxoft.olshevchenko.movieland.entity.enums.Currencies;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import com.luxoft.olshevchenko.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Objects;

/**
 * @author Oleksandr Shevchenko
 */
@Controller
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieRestControllerV1 {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private final MovieService movieService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> showAllMovies(@RequestParam(value = "rating", required = false, defaultValue = "decs") String rating,
                                                     @RequestParam(value = "price", required = false, defaultValue = "decs") String price) {
        logger.info("MovieRestControllerV1 showAllMovies");

        List<Movie> movies;
        if (Objects.equals(rating.toUpperCase(), String.valueOf(Order.ASC)) ||
            Objects.equals(rating.toUpperCase(), String.valueOf(Order.DESC))) {
            movies = movieService.sortByRating(rating);
        } else if (Objects.equals(price.toUpperCase(), String.valueOf(Order.ASC)) ||
                  Objects.equals(price.toUpperCase(), String.valueOf(Order.DESC))){
            movies = movieService.sortByPrice(price);
        } else {
            movies = movieService.getAll();
        }

        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<Movie>> responseEntity = new ResponseEntity<>(movies, HttpStatus.OK);
        logger.info("Status Code {}", responseEntity.getStatusCode());
        return responseEntity;
    }

    @GetMapping(value = "{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovieByIdShort(@PathVariable("movieId") Long movieId,
                                                   @RequestParam(defaultValue = "UAH") String currency) {
        logger.info("MovieRestControllerV1 getMovieByIdShort {}", movieId);

        Movie movie = movieService.getById(movieId, getCurrencyValue(currency));

        if (movieId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<Movie> responseEntity = new ResponseEntity<>(movie, HttpStatus.OK);
        logger.info("Status Code {}", responseEntity.getStatusCode());
        logger.info("Request Body {}", responseEntity.getBody());
        return responseEntity;
    }

    @GetMapping(value = "/random/{quantity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> showRandomMovies(@PathVariable("quantity") Long quantity) {
        logger.info("MovieRestControllerV1 showRandomMovies {}", quantity);

        List<Movie> movies = movieService.getRandom(quantity);

        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<Movie>> responseEntity = new ResponseEntity<>(movies, HttpStatus.OK);
        logger.info("Status Code {}", responseEntity.getStatusCode());
        logger.info("Request Body {}", responseEntity.getBody());
        return responseEntity;
    }

    @GetMapping(value = "genre/{genreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getMoviesByGenreId(@PathVariable("genreId") Long genreId) {
        logger.info("MovieRestControllerV1 getMovieByIdShort {}", genreId);

        List<Movie> movies = movieService.getMoviesByGenreId(genreId);

        if (genreId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (movies == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<Movie>> responseEntity = new ResponseEntity<>(movies, HttpStatus.OK);
        logger.info("Status Code {}", responseEntity.getStatusCode());
        logger.info("Request Body {}", responseEntity.getBody());
        return responseEntity;
    }



    private Currencies getCurrencyValue(String currency) {
        try {
            return Currencies.currencyIgnoreCase(currency);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency parameter: " + currency, e);
        }
    }
}
