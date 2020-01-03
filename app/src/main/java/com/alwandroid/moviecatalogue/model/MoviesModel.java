package com.alwandroid.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class MoviesModel implements Parcelable {

    private int id;
    private String title;
    private String release_date;
    private Double vote_average;
    private String original_language;
    private int vote_count;
    private String overview;
    private String poster_path;
    private String backdrop_path;

    public MoviesModel(){

    }

    public MoviesModel(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt("id");
            this.title = jsonObject.getString("title");
            this.release_date = jsonObject.getString("release_date");
            this.vote_average = jsonObject.getDouble("vote_average");
            this.original_language = jsonObject.getString("original_language");
            this.vote_count = jsonObject.getInt("vote_count");
            this.overview = jsonObject.getString("overview");
            String poster = jsonObject.getString("poster_path");
            this.poster_path = "https://image.tmdb.org/t/p/original"+poster;
            String backdrop = jsonObject.getString("backdrop_path");
            this.backdrop_path = "https://image.tmdb.org/t/p/original"+backdrop;
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    protected MoviesModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        release_date = in.readString();
        if (in.readByte() == 0) {
            vote_average = null;
        } else {
            vote_average = in.readDouble();
        }
        original_language = in.readString();
        vote_count = in.readInt();
        overview = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(release_date);
        if (vote_average == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(vote_average);
        }
        dest.writeString(original_language);
        dest.writeInt(vote_count);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MoviesModel> CREATOR = new Creator<MoviesModel>() {
        @Override
        public MoviesModel createFromParcel(Parcel in) {
            return new MoviesModel(in);
        }

        @Override
        public MoviesModel[] newArray(int size) {
            return new MoviesModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
