package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import java.lang.reflect.Type;
import java.util.ArrayList;
public class MovieDetailsFragment extends Fragment {
    private Movie movie;
    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        Intent intent = requireActivity().getIntent();
        Integer movieID = intent.getIntExtra("movieID", 0);
        try {
            getMovie(movieID);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_details_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void getMovie(Integer movieID) {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String URL = "https://moviesapiserver.azurewebsites.net/api/Movie/id/" + movieID.toString();
        Log.d("Request", "making request");
        try {
            StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET,
                    URL,
                    response -> {
                        movie = new Gson().fromJson(response, Movie.class);
                        buildView(movie);
                    },
                    error -> Log.d("Error", error.toString()));
            queue.add(jsonArrayRequest);
        } catch (Exception e1) {
            Log.d(TAG, e1.toString());
        }
    }

    @SuppressLint("SetTextI18n")
    private void buildView(Movie movie) {
        TextView movieTitle = requireView().findViewById(R.id.movie_title);
        TextView movieGenre = requireView().findViewById(R.id.movie_genre);
        ImageView movieImage = requireView().findViewById(R.id.movie_image);
        TextView movieRating = requireView().findViewById(R.id.movie_ratings);
        TextView movieTime = requireView().findViewById(R.id.movie_duration);
        TextView movieDescription = requireView().findViewById(R.id.movie_description);
        TextView movieScreenings = requireView().findViewById(R.id.movie_screening);
        movieTitle.setText(movie.getName());
        movieGenre.setText(movie.getGenre());
        Picasso.get().load(movie.getThumbnail()).into(movieImage);
        movieRating.setText(Double.toString(movie.getAvgRating()));
        movieTime.setText(movie.getRuntime());
        movieDescription.setText(movie.getDescription());
    }
}
