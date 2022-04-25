package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MovieDetailsFragment extends Fragment {
    private ArrayList<Movie> movies;

    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        try {
            getMovies();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    private void getMovies() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String URL = "https://moviesapiserver.azurewebsites.net/api/Movie";
        Log.d("Request", "making request");
        try {
            StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET,
                    URL,
                    response -> {
                        Type movieListType = new TypeToken<ArrayList<Movie>>(){}.getType();
                        movies = new Gson().fromJson(response, movieListType);
                        buildRecyclerViewList(movies);
                    },
                    error -> Log.d("Error", error.toString()));
            queue.add(jsonArrayRequest);
        } catch (Exception e1) {
            Log.d(TAG, e1.toString());
        }
    }

    private void buildRecyclerViewList(ArrayList<Movie> movies) {
        // initializing our adapter class.
        MovieDetailsAdapter adapter = new MovieDetailsAdapter(movies, requireContext());
        RecyclerView recyclerView = requireView().findViewById(R.id.recycler_view_details);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener((recyclerView1, position, v) -> {
                    Movie movie = movies.get(position);
                    try {
                        Intent intent = new Intent(getContext(), MovieActivity.class);
                        intent.putExtra("movieID", movie.getId());
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e("Intent error", e.getMessage());
                    }
                });
    }
}
