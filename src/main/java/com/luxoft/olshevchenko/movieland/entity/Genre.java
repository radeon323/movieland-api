package com.luxoft.olshevchenko.movieland.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Oleksandr Shevchenko
 */
@Getter
@Setter
@Builder
public class Genre implements Serializable {

    private Long genreId;
    private String genre;
    private Set<Movie> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return this.getGenreId().equals(genre.getGenreId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getGenreId());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "genre_id = " + genreId + ", " +
                "genre = " + genre + ")";
    }
}
