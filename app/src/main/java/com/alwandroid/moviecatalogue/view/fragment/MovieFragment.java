package com.alwandroid.moviecatalogue.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.alwandroid.moviecatalogue.R;
import com.alwandroid.moviecatalogue.apirequest.MovieMainModel;
import com.alwandroid.moviecatalogue.model.MoviesModel;
import com.alwandroid.moviecatalogue.view.activity.MovieDetailActivity;
import com.alwandroid.moviecatalogue.view.adapter.MovieAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private RecyclerView rvMovies;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MovieMainModel moviesMainModel;

        rvMovies = view.findViewById(R.id.rv_movies);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovies.setAdapter(movieAdapter);

        moviesMainModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieMainModel.class);
        moviesMainModel.setListMovies();
        moviesMainModel.getListMovies().observe(this, new Observer<ArrayList<MoviesModel>>() {
            @Override
            public void onChanged(final ArrayList<MoviesModel> list) {
                if (list != null){
                    movieAdapter.setMoviesModels(list);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }


}
