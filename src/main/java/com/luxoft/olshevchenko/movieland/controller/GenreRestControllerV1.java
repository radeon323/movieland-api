package com.luxoft.olshevchenko.movieland.controller;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Oleksandr Shevchenko
 */
@Controller
@RequestMapping("/api/v1/genre")
@RequiredArgsConstructor
public class GenreRestControllerV1 {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private final GenreService genreService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Genre>> showAllGenres() {

        List<Genre> genres = genreService.getAll();

        if (genres.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<Genre>> responseEntity = new ResponseEntity<>(genres, HttpStatus.OK);
        logger.info("Status Code {}", responseEntity.getStatusCode());
        logger.info("Request Body {}", responseEntity.getBody());
        return responseEntity;
    }
}
