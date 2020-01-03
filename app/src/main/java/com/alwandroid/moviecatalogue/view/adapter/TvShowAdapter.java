package com.alwandroid.moviecatalogue.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alwandroid.moviecatalogue.R;
import com.alwandroid.moviecatalogue.model.MoviesModel;
import com.alwandroid.moviecatalogue.model.TvShowModel;
import com.alwandroid.moviecatalogue.view.activity.MovieDetailActivity;
import com.alwandroid.moviecatalogue.view.activity.TvShowDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.CardViewViewHolder> {
    private final ArrayList<TvShowModel> tvShowModels = new ArrayList<>();

    public void setTvShowModel(ArrayList<TvShowModel> list){
        tvShowModels.clear();
        tvShowModels.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_tvshow, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.CardViewViewHolder holder, int position) {
        holder.bind(tvShowModels.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowModels.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvOverview, tvRating;
        ImageView imgPoster;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_tvshow);
            tvTitle = itemView.findViewById(R.id.tv_tvshow_title);
            tvOverview = itemView.findViewById(R.id.tv_tvshow_overview);
            tvRating = itemView.findViewById(R.id.tv_tvshow_ratings);

            itemView.setOnClickListener(this);
        }

        void bind(TvShowModel moviesModel){
            tvTitle.setText(moviesModel.getName());
            tvOverview.setText(moviesModel.getOverview());
            tvRating.setText(Double.toString(moviesModel.getVote_average()));
            Glide.with(itemView)
                    .load(moviesModel.getPoster_path())
                    .into(imgPoster);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TvShowModel tvShow = tvShowModels.get(position);

            Intent intent = new Intent(v.getContext(), TvShowDetailActivity.class);
            intent.putExtra(TvShowDetailActivity.TVSHOW_EXTRA, tvShow);
            v.getContext().startActivity(intent);
        }
    }
}
