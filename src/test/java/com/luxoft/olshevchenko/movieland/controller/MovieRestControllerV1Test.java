package com.luxoft.olshevchenko.movieland.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import com.luxoft.olshevchenko.movieland.service.CurrencyService;
import com.luxoft.olshevchenko.movieland.service.GenreService;
import com.luxoft.olshevchenko.movieland.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieRestControllerV1.class)
class MovieRestControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieService movieService;

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private GenreService genreService;


    @Test
    void testShowAllMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        Movie firstMovie = Movie.builder()
                .movieId(1L)
                .movieName("Star Wars")
                .year(1977)
                .country("GB")
                .description("Luke Skywalker, a young farmer from the desert planet of Tattooine, must save Princess Leia from the evil Darth Vader.")
                .rating(7.4)
                .price(100)
                .genres(new HashSet<>())
                .build();
        movies.add(firstMovie);

        Movie secondMovie = Movie.builder()
                .movieId(2L)
                .movieName("Star Trek")
                .year(1977)
                .country("USA")
                .description("In the 23rd Century, Captain James T. Kirk and the crew of the U.S.S. Enterprise explore the galaxy and defend the United Federation of Planets.")
                .rating(8.5)
                .price(110)
                .genres(new HashSet<>())
                .build();
        movies.add(secondMovie);

        when(movieService.getAll()).thenReturn(movies);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/movie")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].movieName").value("Star Wars"))
                .andExpect(jsonPath("$[0].country").value("GB"))
                .andExpect(jsonPath("$[0].rating").value(7.4))
                .andExpect(jsonPath("$[1].movieName").value("Star Trek"))
                .andExpect(jsonPath("$[1].country").value("USA"))
                .andExpect(jsonPath("$[1].rating").value(8.5));
        verify(movieService, times(1)).getAll();
    }

    @Test
    void testSortMoviesByRating() throws Exception {
        List<Movie> movies = new ArrayList<>();

        Movie firstMovie = Movie.builder()
                .movieId(1L)
                .movieName("Star Wars")
                .year(1977)
                .country("GB")
                .description("Luke Skywalker, a young farmer from the desert planet of Tattooine, must save Princess Leia from the evil Darth Vader.")
                .rating(7.4)
                .price(100)
                .genres(new HashSet<>())
                .build();
        movies.add(firstMovie);

        Movie secondMovie = Movie.builder()
                .movieId(2L)
                .movieName("Star Trek")
                .year(1977)
                .country("USA")
                .description("In the 23rd Century, Captain James T. Kirk and the crew of the U.S.S. Enterprise explore the galaxy and defend the United Federation of Planets.")
                .rating(8.5)
                .price(110)
                .genres(new HashSet<>())
                .build();
        movies.add(secondMovie);

        when(movieService.getAll()).thenReturn(movies);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/movie?rating=acs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].movieName").value("Star Wars"))
                .andExpect(jsonPath("$[0].country").value("GB"))
                .andExpect(jsonPath("$[0].rating").value(7.4))
                .andExpect(jsonPath("$[1].movieName").value("Star Trek"))
                .andExpect(jsonPath("$[1].country").value("USA"))
                .andExpect(jsonPath("$[1].rating").value(8.5));
        verify(movieService, times(1)).getAll();
    }

    @Test
    void testGetMovieByIdShort() throws Exception {
        Movie movie = Movie.builder()
                .movieId(1L)
                .movieName("Star Wars")
                .year(1977)
                .country("GB")
                .description("Luke Skywalker, a young farmer from the desert planet of Tattooine, must save Princess Leia from the evil Darth Vader.")
                .rating(7.4)
                .price(100)
                .genres(new HashSet<>())
                .build();
        String currency = "USD";
        when(currencyService.getCurrencyRateOnThisDay(currency)).thenReturn(29.25);
        when(movieService.getById(1L, null)).thenReturn(movie);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/movie/{movieId}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieId").value(1))
                .andExpect(jsonPath("$.movieName").value("Star Wars"))
                .andExpect(jsonPath("$.year").value(1977));
        verify(movieService, times(1)).getById(1L, null);
    }

    @Test
    void testShowRandomMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        Movie firstMovie = Movie.builder()
                .movieId(1L)
                .movieName("Star Wars")
                .year(1977)
                .country("GB")
                .description("Luke Skywalker, a young farmer from the desert planet of Tattooine, must save Princess Leia from the evil Darth Vader.")
                .rating(7.4)
                .price(100)
                .genres(new HashSet<>())
                .build();
        movies.add(firstMovie);

        when(movieService.getRandom(1L)).thenReturn(movies);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/movie/random/{quantity}",1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].movieName").value("Star Wars"))
                .andExpect(jsonPath("$[0].country").value("GB"))
                .andExpect(jsonPath("$[0].rating").value(7.4));
        verify(movieService, times(1)).getRandom(1L);
    }

    @Test
    void testGetMoviesByGenreId() throws Exception {
        List<Movie> movies = new ArrayList<>();

        Movie firstMovie = Movie.builder()
                .movieId(1L)
                .movieName("Star Wars")
                .year(1977)
                .country("GB")
                .description("Luke Skywalker, a young farmer from the desert planet of Tattooine, must save Princess Leia from the evil Darth Vader.")
                .rating(7.4)
                .price(100)
                .genres(new HashSet<>())
                .build();
        movies.add(firstMovie);

        Movie secondMovie = Movie.builder()
                .movieId(2L)
                .movieName("Star Trek")
                .year(1977)
                .country("USA")
                .description("In the 23rd Century, Captain James T. Kirk and the crew of the U.S.S. Enterprise explore the galaxy and defend the United Federation of Planets.")
                .rating(8.5)
                .price(110)
                .genres(new HashSet<>())
                .build();
        movies.add(secondMovie);

        when(movieService.getMoviesByGenreId(1L)).thenReturn(movies);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/movie/genre/{genreId}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].movieName").value("Star Wars"))
                .andExpect(jsonPath("$[0].country").value("GB"))
                .andExpect(jsonPath("$[0].rating").value(7.4));
        verify(movieService, times(1)).getMoviesByGenreId(1L);
    }
}