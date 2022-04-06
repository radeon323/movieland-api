package com.luxoft.olshevchenko.movieland.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.olshevchenko.movieland.entity.Genre;
import com.luxoft.olshevchenko.movieland.service.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreRestControllerV1.class)
class GenreRestControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GenreService genreService;

    @Test
    void testShowAllGenres() throws Exception {
        List<Genre> genres = new ArrayList<>();

        Genre firstGenre = Genre.builder()
                .genreId(1L)
                .genre("Drama")
                .build();
        genres.add(firstGenre);

        Genre secondGenre = Genre.builder()
                .genreId(2L)
                .genre("Sci-Fi")
                .build();
        genres.add(secondGenre);

        when(genreService.getAll()).thenReturn(genres);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/genre")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].genreId").value(1L))
                .andExpect(jsonPath("$[0].genre").value("Drama"))
                .andExpect(jsonPath("$[1].genreId").value(2L))
                .andExpect(jsonPath("$[1].genre").value("Sci-Fi"));
        verify(genreService, times(1)).getAll();
    }
}