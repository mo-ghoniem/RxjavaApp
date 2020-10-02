package com.example.rxjavaapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rxjavaapp.R;
import com.example.rxjavaapp.data.MoviesClient;
import com.example.rxjavaapp.model.Movies;

import java.util.List;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder> {

    private List<Movies.Result> moviesResults;
    private Context mContext;

    public MoviePosterAdapter(Context context, List<Movies.Result> moviesResults) {
        this.moviesResults = moviesResults;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MoviePosterAdapter.MoviePosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviePosterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.movie_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePosterAdapter.MoviePosterViewHolder holder, int position) {

        String imageUrl = MoviesClient.IMAGE_BASE_URL + moviesResults.get(position).getPosterPath();
        Glide.with(holder.posterIv.getContext()).load(imageUrl).into(holder.posterIv);

    }

    @Override
    public int getItemCount() {
        return moviesResults.size();
    }


    public class MoviePosterViewHolder extends RecyclerView.ViewHolder {
        ImageView posterIv;
        public MoviePosterViewHolder(@NonNull View itemView) {
            super(itemView);

            posterIv = itemView.findViewById(R.id.poster_image);
        }
    }
}
