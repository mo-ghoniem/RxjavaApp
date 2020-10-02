package com.example.rxjavaapp.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rxjavaapp.data.MoviesClient;
import com.example.rxjavaapp.model.Genres;
import com.example.rxjavaapp.model.Movies;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviesViewModel extends ViewModel {

    private static final String TAG = "MoviesViewModel";
    List<List<Movies.Result>> moviesRes = new ArrayList<>();

    public MutableLiveData<Movies> mutableResults = new MutableLiveData<>();
    public MutableLiveData<List<Genres.GenresBean>> mutableGenres = new MutableLiveData<>();
    public List<Integer> moviesIds = new ArrayList<>();

    public void getGenres(){
        Observable<Genres> genresObservable = MoviesClient.getInstance().getMoviesGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        genresObservable.subscribe(new Observer<Genres>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Genres genres) {
                for(int i = 0; i < genres.getGenres().size(); i++){
                    moviesIds.add(genres.getGenres().get(i).getId());
                }

                for (int i = 0; i < moviesIds.size(); i++){
                    getMoviesByGenre(moviesIds.get(i));
                    Log.d(TAG, "onNext00: " + moviesIds.size());
                }


            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void getMoviesByGenre(int genre){
       @NonNull Observable<Movies> moviesObservable = MoviesClient.getInstance().getMoviesWithGenre(genre)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
       moviesObservable.subscribe(new Observer<Movies>() {
           @Override
           public void onSubscribe(@NonNull Disposable d) {

           }

           @Override
           public void onNext(@NonNull Movies movies) {
               mutableResults.setValue(movies);

           }

           @Override
           public void onError(@NonNull Throwable e) {

           }

           @Override
           public void onComplete() {

           }
       });

    }

    public void getGenresNames(){
        Observable<Genres> genresObservable = MoviesClient.getInstance().getMoviesGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        genresObservable.subscribe(new Observer<Genres>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Genres genres) {
                mutableGenres.setValue(genres.getGenres());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }





}
