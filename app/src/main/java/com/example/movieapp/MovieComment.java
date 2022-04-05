package com.example.movieapp;

import java.time.Instant;

public class MovieComment {
    private int id;
    private String comment;
    private Instant created;
    private int movieId;
    private Movie movie;

    public MovieComment(int id, String comment, Instant created, int movieId, Movie movie) {
        this.id = id;
        this.comment = comment;
        this.created = created;
        this.movieId = movieId;
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
