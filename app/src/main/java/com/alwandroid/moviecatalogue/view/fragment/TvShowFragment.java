package com.alwandroid.moviecatalogue.view.fragment;


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
import com.alwandroid.moviecatalogue.apirequest.TvShowMainModel;
import com.alwandroid.moviecatalogue.model.TvShowModel;
import com.alwandroid.moviecatalogue.view.adapter.TvShowAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TvShowAdapter tvShowAdapter;
    private ProgressBar progressBar;
    private RecyclerView rvTvShow;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TvShowMainModel tvShowMainModel;

        rvTvShow = view.findViewById(R.id.rv_tvshow);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        tvShowAdapter = new TvShowAdapter();
        tvShowAdapter.notifyDataSetChanged();
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTvShow.setAdapter(tvShowAdapter);

        tvShowMainModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowMainModel.class);
        tvShowMainModel.setListMovies();
        tvShowMainModel.getListMovies().observe(this, new Observer<ArrayList<TvShowModel>>() {
            @Override
            public void onChanged(ArrayList<TvShowModel> tvShowModels) {
                if (tvShowModels != null){
                    tvShowAdapter.setTvShowModel(tvShowModels);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}
