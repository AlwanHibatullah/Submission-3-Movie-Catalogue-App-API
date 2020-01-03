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
import com.alwandroid.moviecatalogue.view.activity.MovieDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder> {
    private final ArrayList<MoviesModel> moviesModels = new ArrayList<>();

    public void setMoviesModels(ArrayList<MoviesModel> list){
        moviesModels.clear();
        moviesModels.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_movie, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.CardViewViewHolder holder, final int position) {
        holder.bind(moviesModels.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesModels.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvOverview, tvRating;
        ImageView imgPoster;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_movies);
            tvTitle = itemView.findViewById(R.id.tv_movie_title);
            tvOverview = itemView.findViewById(R.id.tv_movie_overview);
            tvRating = itemView.findViewById(R.id.tv_movie_ratings);

            itemView.setOnClickListener(this);
        }

        void bind(MoviesModel moviesModel){
            tvTitle.setText(moviesModel.getTitle());
            tvOverview.setText(moviesModel.getOverview());
            tvRating.setText(Double.toString(moviesModel.getVote_average()));
            Glide.with(itemView)
                    .load(moviesModel.getPoster_path())
                    .into(imgPoster);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            MoviesModel movie = moviesModels.get(position);

            Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
            v.getContext().startActivity(intent);
        }
    }
}
