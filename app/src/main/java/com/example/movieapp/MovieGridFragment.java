package com.example.movieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MovieGridFragment extends Fragment {

    private RecyclerView recyclerView;
    private final List<Movie> movieList = new ArrayList<Movie>();

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
        View view = inflater.inflate(R.layout.movie_grid_fragment, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false));
        MovieListAdapter adapter = new MovieListAdapter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void getMovies() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET,
                "https://albumtrackrapi.azurewebsites.net/api/AlbumList/", null,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }

    }
}
