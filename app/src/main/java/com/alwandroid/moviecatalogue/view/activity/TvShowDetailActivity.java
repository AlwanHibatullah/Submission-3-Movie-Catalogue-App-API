package com.alwandroid.moviecatalogue.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alwandroid.moviecatalogue.R;
import com.alwandroid.moviecatalogue.model.TvShowModel;
import com.bumptech.glide.Glide;

public class TvShowDetailActivity extends AppCompatActivity {

    TextView tvTitle, tvReleaseDate, tvRatings, tvOrig_lang, tvVoteCount, tvOverview;
    ImageView imgPoster, imgBackdrop;
    public static final String TVSHOW_EXTRA = "tvshow_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        imgBackdrop = findViewById(R.id.img_tvshow_backdrop);
        imgPoster = findViewById(R.id.img_tvshow_detail);
        tvTitle = findViewById(R.id.tv_title_detail);
        tvReleaseDate = findViewById(R.id.tv_year_detail);
        tvRatings = findViewById(R.id.tv_tvshow_ratings);
        tvOrig_lang = findViewById(R.id.tv_language);
        tvVoteCount = findViewById(R.id.tv_vote);
        tvOverview = findViewById(R.id.tv_synopsis_detail);

        TvShowModel tvShowModel = getIntent().getParcelableExtra(TVSHOW_EXTRA);

        Glide.with(this)
                .load(tvShowModel.getBackdrop_path())
                .into(imgBackdrop);
        Glide.with(this)
                .load(tvShowModel.getPoster_path())
                .into(imgPoster);
        tvTitle.setText(tvShowModel.getName());

        String first_date_air = tvShowModel.getfirst_air_date();
        String dateArray[] = first_date_air.split("-");
        String year = dateArray[0];
        tvReleaseDate.setText(year);

        tvRatings.setText(Double.toString(tvShowModel.getVote_average()));
        tvOrig_lang.setText(tvShowModel.getOriginal_language());
        tvVoteCount.setText(Integer.toString(tvShowModel.getVote_count()));
        tvOverview.setText(tvShowModel.getOverview());

        getSupportActionBar().setTitle(tvShowModel.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}
