package com.example.movieapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MoviesGridFragment extends Fragment {

    private RecyclerView recyclerView;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_grid_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void getMovies() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String URL = "https://moviesapiserver.azurewebsites.net/api/Movie";
        Log.d("Request", "making request");
        try {
            StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET,
                    URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Type movieListType = new TypeToken<ArrayList<Movie>>(){}.getType();
                            movies = new Gson().fromJson(response, movieListType);
                            buildRecyclerViewList(movies);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error", error.toString());
                        }
                    });
            queue.add(jsonArrayRequest);
        } catch (Exception e1) {
            Log.d(TAG, e1.toString());
        }
    }

    private void buildRecyclerViewList(ArrayList<Movie> movies) {
        // initializing our adapter class.
        MovieListAdapter adapter = new MovieListAdapter(movies, requireContext());
        recyclerView = requireView().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Movie movie = movies.get(position);
                        try {
                            Intent intent = new Intent(getContext(), MovieActivity.class);
                            intent.putExtra("movieID", movie.getId());
                            startActivity(intent);
                        } catch (Exception e) {
                            Log.e("Intent error", e.getMessage());
                        }
                    }
                });
    }
}
