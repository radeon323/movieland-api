package com.luxoft.olshevchenko.movieland.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Oleksandr Shevchenko
 */
@Getter
@Setter
@EqualsAndHashCode
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
