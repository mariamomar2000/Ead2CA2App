package com.example.movieapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MoviesGridFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Movie> movies;
    private MovieListAdapter adapter;
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
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.movie_grid_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        requireActivity().getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setVisibility(View.VISIBLE);
        EditText txtSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        txtSearch.setTextColor(Color.WHITE);
        searchView.setQueryHint("Search for movie");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (recyclerView != null) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void getMovies() {
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
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                                Button button = requireActivity().requireViewById(R.id.retry_btn);
                                TextView textView = requireActivity().requireViewById(R.id.api_error);
                                textView.setVisibility(View.VISIBLE);
                                button.setVisibility(View.VISIBLE);
                                textView.setText(requireContext().getString(R.string.something_went_wrong,
                                        error.toString()));
                            }
                        }
                    });
            queue.add(jsonArrayRequest);
        } catch (Exception e1) {
            Log.d(TAG, e1.toString());
        }
    }

    private void buildRecyclerViewList(ArrayList<Movie> movies) {
        // initializing our adapter class.
        adapter = new MovieListAdapter(movies, requireContext());
        recyclerView = requireView().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false));
        int largePadding = getResources().getDimensionPixelSize(R.dimen.movie_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.movie_grid_spacing_small);
        recyclerView.addItemDecoration(new MovieGridItemDecoration(largePadding, smallPadding));
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Movie movie = movies.get(position);
                        try {
                            Intent intent = new Intent(requireContext(), MovieActivity.class);
                            intent.putExtra("movieID", movie.getId());
                            startActivity(intent);
                        } catch (Exception e) {
                            Log.e("Intent error", e.getMessage());
                        }
                    }
                });
    }
}
