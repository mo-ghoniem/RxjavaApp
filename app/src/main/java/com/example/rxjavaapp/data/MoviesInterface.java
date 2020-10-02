package com.example.rxjavaapp.data;

import com.example.rxjavaapp.model.Genres;
import com.example.rxjavaapp.model.Movies;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesInterface {

    @GET("movie/popular")
    Observable<Movies> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Observable<Movies> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Observable<Genres> getMoviesGenres(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Observable<Movies> getMovieWithGenre(@Query("api_key") String apiKey, @Query("with_genres") int with_genres);

}
