package com.luxoft.olshevchenko.movieland.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<Movie>> showAllMovies() {

        List<Movie> movies = movieService.getAll();

        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<Movie>> responseEntity = new ResponseEntity<>(movies, HttpStatus.OK);
        logger.info("Status Code {}", responseEntity.getStatusCode());
        logger.info("Request Body {}", responseEntity.getBody());
        return responseEntity;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovieByIdShort(@PathVariable("id") Long movieId) {
        logger.info("MovieRestControllerV1 getMovieByIdShort {}", movieId);

        Movie movie = movieService.getById(movieId);


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

        List<Movie> movies = movieService.getRandom(quantity);

        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<Movie>> responseEntity = new ResponseEntity<>(movies, HttpStatus.OK);
        logger.info("Status Code {}", responseEntity.getStatusCode());
        logger.info("Request Body {}", responseEntity.getBody());
        return responseEntity;
    }
}
