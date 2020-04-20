package com.example.movieapp.trailers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.movieapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrailersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String id;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers);
        Bundle bundle =getIntent().getExtras();
        id =bundle.getString("id");
        context =this;
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/" + id + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterfaceTrailers apiInterfaceTrailers =retrofit.create(ApiInterfaceTrailers.class);
        Call<ModelTrailers> call = apiInterfaceTrailers.getTrailers();

        call.enqueue(new Callback<ModelTrailers>() {
            @Override
            public void onResponse(Call<ModelTrailers> call, Response<ModelTrailers> response) {
                ModelTrailers modelTrailers =response.body();
                TrailersAdapter trailersAdapter =new TrailersAdapter(context, modelTrailers.getTrailers());
                recyclerView.setAdapter(trailersAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<ModelTrailers> call, Throwable t) {
                Toast.makeText(context, "please check your internet", Toast.LENGTH_LONG).show();
                Toast.makeText(context,t.getMessage().toString(),Toast.LENGTH_LONG).show();
                Log.e("kkkkkkkkkkkkkkkk",t.getMessage().toString());
            }
        });
    }
}
