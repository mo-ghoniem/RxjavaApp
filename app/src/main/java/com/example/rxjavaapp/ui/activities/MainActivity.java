package com.example.rxjavaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;

import com.example.rxjavaapp.R;
import com.example.rxjavaapp.model.Genres;
import com.example.rxjavaapp.model.Movies;
import com.example.rxjavaapp.ui.adapters.MoviePosterAdapter;
import com.example.rxjavaapp.ui.adapters.MoviesAdapter;
import com.example.rxjavaapp.ui.viewmodels.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView movieRv;
    MoviesViewModel moviesViewModel;

    RecyclerView moviePosterRv;
    MoviesAdapter adapter;

    List<String> genresNames = new ArrayList<>();
    List<List<Movies.Result>> movies = new ArrayList<>();

    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        moviePosterRv = findViewById(R.id.movie_rv);


        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        moviesViewModel.getGenresNames();
        moviesViewModel.mutableGenres.observe(this, new Observer<List<Genres.GenresBean>>() {
            @Override
            public void onChanged(List<Genres.GenresBean> genresBeans) {
                for (int i = 0; i < genresBeans.size(); i++){
                    genresNames.add(genresBeans.get(i).getName());
                }
            }
        });

        moviesViewModel.getGenres();
        moviesViewModel.mutableResults.observe(this, new Observer<Movies>() {
            @Override
            public void onChanged(Movies moviesResults) {
                adapter.addMovies(moviesResults);
            }
        });

        initRecycler(context, genresNames, movies);


    }

    private void initRecycler(Context context, List<String> genresNames, List<List<Movies.Result>> moviesResults){
        movieRv = findViewById(R.id.movie_rv);
        movieRv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MoviesAdapter(context, genresNames, moviesResults);
        movieRv.setAdapter(adapter);
    }
}