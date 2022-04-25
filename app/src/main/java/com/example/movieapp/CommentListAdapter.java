package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder>{

    private final ArrayList<MovieComment> comments;
    private final Context mContext;


    public CommentListAdapter(ArrayList<MovieComment> commentArrayList, Context context) {
        this.mContext = context;
        this.comments = commentArrayList;
    }

    @NonNull
    @Override
    public CommentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        holder.bindData(comments.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        public ImageView comment;
        public TextView timeCreated;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment);
            timeCreated = itemView.findViewById(R.id.timeCreated);
        }

        @SuppressLint("SetTextI18n")
        public void bindData(MovieComment movie, Context context) {

        }
    }

}
