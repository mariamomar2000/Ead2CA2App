package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> implements Filterable {

    private final ArrayList<Movie> moviesFull;
    private final ArrayList<Movie> movies;
    private final Context mContext;


    public MovieListAdapter(ArrayList<Movie> movieArrayList, Context context) {
        this.mContext = context;
        this.movies = movieArrayList;
        this.moviesFull = new ArrayList<>(movieArrayList);
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

    @Override
    public Filter getFilter() {
        return movieFilter;
    }

    private final Filter movieFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Movie> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(moviesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Movie movie : moviesFull) {
                    if (movie.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(movie);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            movies.clear();
            movies.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        public ImageView movieImage;
        public TextView movieTitle;
        public TextView movieGenre;
        public TextView movieScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieGenre = itemView.findViewById(R.id.movie_genre);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieScore = itemView.findViewById(R.id.movie_score);
        }

        @SuppressLint("SetTextI18n")
        public void bindData(Movie movie, Context context) {
            movieTitle.setText(movie.getName());
            movieGenre.setText(movie.getGenre());
            movieScore.setText(Double.toString(movie.getAvgRating()));
            Picasso.get().load(movie.getThumbnail()).into(movieImage);
        }
    }

}
