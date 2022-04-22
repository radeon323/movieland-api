package com.luxoft.olshevchenko.movieland.service;

import com.luxoft.olshevchenko.movieland.entity.enums.Currencies;
import com.luxoft.olshevchenko.movieland.entity.Movie;
import com.luxoft.olshevchenko.movieland.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private CurrencyService currencyService;

    @Test
    void testGetAll() {
        MovieService movieService = new MovieService(movieRepository, currencyService);
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

        Mockito.when(movieRepository.getAll()).thenReturn(movies);

        List<Movie> actualMovies = movieService.getAll();
        assertNotNull(actualMovies);
        assertEquals(2, actualMovies.size());
        assertEquals("Star Trek", actualMovies.get(1).getMovieName());
        assertEquals(firstMovie, actualMovies.get(0));
    }

    @Test
    void testGetById() {
        MovieService movieService = new MovieService(movieRepository, currencyService);
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

        Mockito.when(movieRepository.getById(1L)).thenReturn(movie);
        Movie actualMovie = movieService.getById(1L, Currencies.UAH);

        assertEquals("Star Wars", actualMovie.getMovieName());
        assertEquals("GB", actualMovie.getCountry());
        assertEquals(7.4, actualMovie.getRating());
    }

    @Test
    void testGetRandom() {
        MovieService movieService = new MovieService(movieRepository, currencyService);
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

        Mockito.when(movieRepository.getRandom(2L)).thenReturn(movies);

        List<Movie> actualMovies = movieService.getRandom(2L);
        assertNotNull(actualMovies);
        assertEquals(2, actualMovies.size());
        assertEquals("Star Trek", actualMovies.get(1).getMovieName());
        assertEquals(firstMovie, actualMovies.get(0));
    }

    @Test
    void testSortByRating() {
        MovieService movieService = new MovieService(movieRepository, currencyService);
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

        Mockito.when(movieRepository.sortByRating("asc")).thenReturn(movies);

        List<Movie> actualMovies = movieService.sortByRating("asc");
        assertNotNull(actualMovies);
        assertEquals(2, actualMovies.size());
        assertEquals("Star Trek", actualMovies.get(1).getMovieName());
        assertEquals(firstMovie, actualMovies.get(0));
    }

    @Test
    void testGetMoviesByGenreId() {
        MovieService movieService = new MovieService(movieRepository, currencyService);
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

        Mockito.when(movieRepository.getByGenre(1L)).thenReturn(movies);

        List<Movie> actualMovies = movieService.getMoviesByGenreId(1L);
        assertNotNull(actualMovies);
        assertEquals(2, actualMovies.size());
        assertEquals("Star Trek", actualMovies.get(1).getMovieName());
        assertEquals(firstMovie, actualMovies.get(0));
    }
}