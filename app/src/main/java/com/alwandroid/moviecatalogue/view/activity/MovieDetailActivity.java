package com.alwandroid.moviecatalogue.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alwandroid.moviecatalogue.R;
import com.alwandroid.moviecatalogue.model.MoviesModel;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {
    TextView tvTitle, tvReleaseDate, tvRatings, tvOrig_lang, tvVoteCount, tvOverview;
    ImageView imgPoster, imgBackdrop;
    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imgBackdrop = findViewById(R.id.img_movies_backdrop);
        imgPoster = findViewById(R.id.img_movies_detail);
        tvTitle = findViewById(R.id.tv_title_detail);
        tvReleaseDate = findViewById(R.id.tv_year_detail);
        tvRatings = findViewById(R.id.tv_movie_ratings);
        tvOrig_lang = findViewById(R.id.tv_language);
        tvVoteCount = findViewById(R.id.tv_vote);
        tvOverview = findViewById(R.id.tv_synopsis_detail);

        MoviesModel moviesModel = getIntent().getParcelableExtra(EXTRA_MOVIE);

        Glide.with(this)
                .load(moviesModel.getBackdrop_path())
                .into(imgBackdrop);
        Glide.with(this)
                .load(moviesModel.getPoster_path())
                .into(imgPoster);
        tvTitle.setText(moviesModel.getTitle());

        String release_date = moviesModel.getRelease_date();
        String dateArray[] = release_date.split("-");
        String year = dateArray[0];
        tvReleaseDate.setText(year);

        tvRatings.setText(Double.toString(moviesModel.getVote_average()));
        tvOrig_lang.setText(moviesModel.getOriginal_language());
        tvVoteCount.setText(Integer.toString(moviesModel.getVote_count()));
        tvOverview.setText(moviesModel.getOverview());

        getSupportActionBar().setTitle(moviesModel.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}
