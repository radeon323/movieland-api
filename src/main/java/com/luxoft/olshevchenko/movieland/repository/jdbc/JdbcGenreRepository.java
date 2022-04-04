package com.luxoft.olshevchenko.movieland.repository.jdbc;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.repository.GenreRepository;
import com.luxoft.olshevchenko.movieland.repository.mapper.GenreRowMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

/**
 * @author Oleksandr Shevchenko
 */
@Repository
@RequiredArgsConstructor
public class JdbcGenreRepository implements GenreRepository {
    Logger logger = LoggerFactory.getLogger(getClass());

    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();
    private final JdbcTemplate jdbcTemplate;
    private static final List<Genre> cachedGenreList = new ArrayList<>();
    private static final String FIND_ALL = "SELECT genre_id, genre FROM genres;";

    @Override
    public List<Genre> getAll() {
        List<Genre> genres;

        LocalTime startTime = LocalTime.now();
        System.out.println("startTime" + startTime);
        LocalTime endTime = startTime.plusSeconds(5);
        System.out.println("endTime" + endTime);

        if (cachedGenreList.size() == 0) {
            genres = jdbcTemplate.query(FIND_ALL, GENRE_ROW_MAPPER);
            cachedGenreList.addAll(genres);
            logger.info("Get genres without cache");
            return genres;
        } else {
            logger.info("Get genres from cachedGenreList");
            return cachedGenreList;
        }
    }
}
