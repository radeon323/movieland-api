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

    private Long movie_id;
    private String movie_name;
    private int year;
    private String country;
    private String description;
    private double rating;
    private double price;
    private Set<Genre> genres = new HashSet<>();

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return this.getMovie_id().equals(movie.getMovie_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMovie_id());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "movie_id = " + movie_id + ", " +
                "movieName = " + movie_name + ", " +
                "year = " + year + ", " +
                "county = " + country + ", " +
                "description = " + description + ", " +
                "rating = " + rating + ", " +
                "price = " + price + ")";
    }
}
