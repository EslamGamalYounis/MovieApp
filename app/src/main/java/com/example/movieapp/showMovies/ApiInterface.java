package com.example.movieapp.showMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

    public interface ApiInterface {
    @GET("?api_key=52fef61a7dfa11b49d1bf5b61451a138")
    public Call<ModelList> getMovies();
}
