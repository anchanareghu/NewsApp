package com.example.mynewsapp;

import android.content.Context;
import android.widget.Toast;

import Models.NewsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiRequestManager {
    Context context;
    public ApiRequestManager(Context context) {
        this.context = context;
    }
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsHeadLines(OnFetchDataListener fetchDataListener, String category, String query) {
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsApi> call = callNewsApi.headlinesApiCall("in", category, query, context.getString(R.string.API_KEY));

        try {
            call.enqueue(new Callback<NewsApi>() {
                @Override
                public void onResponse(Call<NewsApi> call, Response<NewsApi> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Request Failed !", Toast.LENGTH_SHORT).show();
                    }
                    assert response.body() != null;
                    fetchDataListener.onFetchData(response.body().getArticles(), response.message());

                }
                @Override
                public void onFailure(Call<NewsApi> call, Throwable t) {
                    fetchDataListener.onError("Request Failed !");
                }
            });
        } catch (Exception e) {
            fetchDataListener.onError("Request Failed !");
        }

    }

    public interface CallNewsApi {
        @GET("top-headlines")
        Call<NewsApi> headlinesApiCall(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_key
        );
    }
}
