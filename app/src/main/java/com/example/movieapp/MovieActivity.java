package com.example.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MovieActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new MovieDetailsFragment())
                    .commit();
        }
    }

}
