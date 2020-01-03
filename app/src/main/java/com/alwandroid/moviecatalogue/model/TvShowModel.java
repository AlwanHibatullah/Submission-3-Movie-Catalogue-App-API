package com.alwandroid.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class TvShowModel implements Parcelable {

    private int id;
    private String name;
    private String first_air_date;
    private Double vote_average;
    private String original_language;
    private int vote_count;
    private String overview;
    private String poster_path;
    private String backdrop_path;

    public TvShowModel(){

    }

    public TvShowModel(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt("id");
            this.name = jsonObject.getString("name");
            this.first_air_date = jsonObject.getString("first_air_date");
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

    protected TvShowModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        first_air_date = in.readString();
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
        dest.writeString(name);
        dest.writeString(first_air_date);
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

    public static final Creator<TvShowModel> CREATOR = new Creator<TvShowModel>() {
        @Override
        public TvShowModel createFromParcel(Parcel in) {
            return new TvShowModel(in);
        }

        @Override
        public TvShowModel[] newArray(int size) {
            return new TvShowModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getfirst_air_date() {
        return first_air_date;
    }

    public void setfirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
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
