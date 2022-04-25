package com.example.movieapp;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {


    private final ArrayList<Movie> movies;
    private final Context mContext;

    public MovieListAdapter(ArrayList<Movie> movieArrayList, Context context) {
        this.mContext = context;
        this.movies = movieArrayList;
    }

    @NonNull
    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        holder.bindData(movies.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return movies.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        public ImageView movieImage;
        public TextView movieTitle;
        public TextView movieGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieGenre = itemView.findViewById(R.id.movie_genre);
            movieImage = itemView.findViewById(R.id.movie_image);
        }

        public void bindData(Movie movie, Context context) {
            movieTitle.setText(movie.getName());
            movieGenre.setText(movie.getGenre());
            Picasso.get().load(movie.getThumbnail()).into(movieImage);
        }
    }

}
