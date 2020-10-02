package com.example.rxjavaapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaapp.R;
import com.example.rxjavaapp.model.Genres;
import com.example.rxjavaapp.model.Movies;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<List<Movies.Result>> moviesResults;
//    private Movies.Result moviesResults;
    private Context mContext;
    private List<String> moviesNames;

    public MoviesAdapter(Context context, List<String> moviesNames, List<List<Movies.Result>> moviesResults) {
        this.moviesNames = moviesNames;
        this.moviesResults = moviesResults;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder holder, int position) {
        holder.movieTitle.setText(moviesNames.get(position));
        setCatItemRecycler(holder.itemRecycler,  moviesResults.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesNames.size();
    }

    public void addMovies(Movies moviesResult){
        for(int i = 0; i < moviesNames.size(); i++){
            moviesResults.add(moviesResult.getResults());
            notifyDataSetChanged();
        }
        notifyDataSetChanged();

    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        RecyclerView itemRecycler;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.cat_title);
            itemRecycler = itemView.findViewById(R.id.item_recycler);

        }
    }

    private void setCatItemRecycler(RecyclerView recyclerView, List<Movies.Result> moviesResults){

        MoviePosterAdapter posterAdapter = new MoviePosterAdapter(mContext, moviesResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(posterAdapter);


    }
}
