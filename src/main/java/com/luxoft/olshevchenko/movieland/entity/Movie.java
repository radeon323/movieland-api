package com.luxoft.olshevchenko.movieland.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Oleksandr Shevchenko
 */
@Getter
@Setter
@Builder
public class Movie implements Serializable {

    private Long movieId;
    private String movieName;
    private int year;
    private String country;
    private String description;
    private double rating;
    private double price;
    private Set<Genre> genres;

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return this.getMovieId().equals(movie.getMovieId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMovieId());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "movie_id = " + movieId + ", " +
                "movieName = " + movieName + ", " +
                "year = " + year + ", " +
                "county = " + country + ", " +
                "description = " + description + ", " +
                "rating = " + rating + ", " +
                "price = " + price + ")";
    }
}
