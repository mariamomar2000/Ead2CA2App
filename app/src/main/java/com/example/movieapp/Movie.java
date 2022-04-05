package com.example.movieapp;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private String description;
    private String genre;
    private int rating;
    private int count;
    private int avgRating;
    private String thumbnail;
    private String screenings;
    private String runtime;
    List<MovieComment> movieComments;

    public Movie(int id, String name, String description, String genre, int rating, int count,
                 int avgRating, String thumbnail, String screenings, String runtime,
                 ArrayList<MovieComment> movieComments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.count = count;
        this.avgRating = avgRating;
        this.thumbnail = thumbnail;
        this.screenings = screenings;
        this.runtime = runtime;
        this.movieComments = movieComments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getScreenings() {
        return screenings;
    }

    public void setScreenings(String screenings) {
        this.screenings = screenings;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public List<MovieComment> getMovieComments() {
        return movieComments;
    }

    public void setMovieComments(List<MovieComment> movieComments) {
        this.movieComments = movieComments;
    }
}
