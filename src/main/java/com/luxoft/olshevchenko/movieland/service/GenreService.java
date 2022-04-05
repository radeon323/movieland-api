package com.luxoft.olshevchenko.movieland.service;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.repository.GenreRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Oleksandr Shevchenko
 */
@Service
@RequiredArgsConstructor
@EnableScheduling
public class GenreService {
    Logger logger = LoggerFactory.getLogger(getClass());
    private final GenreRepository genreRepository;
    private static List<Genre> cachedGenreList = new ArrayList<>();

    public List<Genre> getAll() {
        List<Genre> genres;
        if (cachedGenreList.size() == 0) {
            genres = genreRepository.getAll();
            cachedGenreList.addAll(genres);
            logger.info("Get genres without cache");
            return genres;
        } else {
            logger.info("Get genres from cachedGenreList");
            return cachedGenreList;
        }
    }

    @Scheduled(cron = "${cron.interval}")
    private void clearCache(){
        logger.info("Clearing cache....");
        for (Genre genre : cachedGenreList) {
            genre = null;
        }
        cachedGenreList = new ArrayList<>();
    }


}


