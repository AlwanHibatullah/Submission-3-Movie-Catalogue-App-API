package com.alwandroid.moviecatalogue.apirequest;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alwandroid.moviecatalogue.model.MoviesModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieMainModel extends ViewModel {
    private static final String API = "b9ad44145d0b26e0f040a71bd01ff23c";
    private MutableLiveData<ArrayList<MoviesModel>> listMovies = new MutableLiveData<>();

    public void setListMovies(){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final ArrayList<MoviesModel> listItemsMovies = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API+"&language=en-US";
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i=0;i<list.length();i++){
                        JSONObject movies = list.getJSONObject(i);
                        MoviesModel moviesModel = new MoviesModel(movies);
                        listItemsMovies.add(moviesModel);
                    }
                    listMovies.postValue(listItemsMovies);
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

    public LiveData<ArrayList<MoviesModel>> getListMovies(){
        return listMovies;
    }
}
