package com.alwandroid.moviecatalogue.apirequest;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alwandroid.moviecatalogue.model.MoviesModel;
import com.alwandroid.moviecatalogue.model.TvShowModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowMainModel extends ViewModel {
    private static final String API = "YOUR_API_KEY";
    private MutableLiveData<ArrayList<TvShowModel>> listTvShow = new MutableLiveData<>();

    public void setListMovies(){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final ArrayList<TvShowModel> listItemsTvShow = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key="+API+"&language=en-US&include_null_first_air_dates=false";
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i=0;i<list.length();i++){
                        JSONObject tvShow = list.getJSONObject(i);
                        TvShowModel tvShowModel = new TvShowModel(tvShow);
                        listItemsTvShow.add(tvShowModel);
                    }
                    listTvShow.postValue(listItemsTvShow);
                } catch (Exception e){
                    Log.d("Exception : ", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure : ", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<TvShowModel>> getListMovies(){
        return listTvShow;
    }
}
