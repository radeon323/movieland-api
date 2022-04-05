package com.luxoft.olshevchenko.movieland.repository.jdbc;

import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.repository.GenreRepository;
import com.luxoft.olshevchenko.movieland.repository.mapper.GenreRowMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        return jdbcTemplate.query(FIND_ALL, GENRE_ROW_MAPPER);
    }
}
