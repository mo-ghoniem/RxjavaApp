package com.example.rxjavaapp.data;

import com.example.rxjavaapp.model.Genres;
import com.example.rxjavaapp.model.Movies;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "f588cc300173db43ba966002ee96f41d";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/";


    private static MoviesClient INSTANCE;
    private static MoviesInterface moviesInterface;

    public MoviesClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        moviesInterface = retrofit.create(MoviesInterface.class);
    }

    public static MoviesClient getInstance() {
        if(INSTANCE == null ) {
            INSTANCE = new MoviesClient();
        }
        return INSTANCE;
    }

    public Observable<Movies> getPopular() {
        return moviesInterface.getPopularMovies(API_KEY);
    }

    public Observable<Movies> getNowPlaying(){
        return moviesInterface.getNowPlayingMovies(API_KEY);
    }

    public Observable<Genres> getMoviesGenres(){
        return moviesInterface.getMoviesGenres(API_KEY);
    }

    public Observable<Movies> getMoviesWithGenre(int genre){
        return moviesInterface.getMovieWithGenre(API_KEY, genre);
    }
}
