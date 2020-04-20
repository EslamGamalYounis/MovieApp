package com.example.movieapp.showMovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.movieapp.MoviesDatabase.AppDatabase;
import com.example.movieapp.MoviesDatabase.Movie;
import com.example.movieapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Context context;
    RecyclerView recyclerView;
    String filmType="popular";

    public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        recyclerView =findViewById(R.id.recyclerView);
        appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"moviedb")
                .allowMainThreadQueries().build();

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/"+filmType+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<ModelList> call =apiInterface.getMovies();
        call.enqueue(new Callback<ModelList>() {
            @Override
            public void onResponse(Call<ModelList> call, Response<ModelList> response) {
                //Log.e("llllllllllllllll", response.body().toString() );
                ModelList moviesList =response.body();
               // Log.i("ggggggg", moviesList.getMovie().get(1).getPoster_path());
                MyMoviesAdapter myMoviesAdapter =new MyMoviesAdapter(moviesList.getMovie(),context,filmType);
                recyclerView.setAdapter(myMoviesAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<ModelList> call, Throwable t) {
                Toast.makeText(context, "please check your internet", Toast.LENGTH_LONG).show();
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.i("kkkkkkkkkkkkkkkk",t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mymenu =getMenuInflater();
        mymenu.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();

        if (id==R.id.popular){
            filmType="popular";
            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/movie/"+filmType+"/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            Call<ModelList> call =apiInterface.getMovies();
            call.enqueue(new Callback<ModelList>() {
                @Override
                public void onResponse(Call<ModelList> call, Response<ModelList> response) {
                    //Log.e("llllllllllllllll", response.body().toString() );
                    ModelList moviesList =response.body();
                    // Log.i("ggggggg", moviesList.getMovie().get(1).getPoster_path());
                    MyMoviesAdapter myMoviesAdapter =new MyMoviesAdapter(moviesList.getMovie(),context,filmType);
                    recyclerView.setAdapter(myMoviesAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }

                @Override
                public void onFailure(Call<ModelList> call, Throwable t) {
                    Toast.makeText(context, "please check your internet", Toast.LENGTH_LONG).show();
                    Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                    Log.i("kkkkkkkkkkkkkkkk",t.getMessage());
                }
            });

        }
        else if (id ==R.id.top_rated){
            filmType="top_rated";
            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/movie/"+filmType+"/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            Call<ModelList> call =apiInterface.getMovies();
            call.enqueue(new Callback<ModelList>() {
                @Override
                public void onResponse(Call<ModelList> call, Response<ModelList> response) {
                    //Log.e("llllllllllllllll", response.body().toString() );
                    ModelList moviesList =response.body();
                    // Log.i("ggggggg", moviesList.getMovie().get(1).getPoster_path());
                    MyMoviesAdapter myMoviesAdapter =new MyMoviesAdapter(moviesList.getMovie(),context,filmType);
                    recyclerView.setAdapter(myMoviesAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }

                @Override
                public void onFailure(Call<ModelList> call, Throwable t) {
                    Toast.makeText(context, "please check your internet", Toast.LENGTH_LONG).show();
                    Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                    Log.i("kkkkkkkkkkkkkkkk",t.getMessage());
                }
            });

        }
        else if (id ==R.id.favorite){
            ArrayList<MovieModel> movieLists = new ArrayList<>();
            List<Movie> movies = appDatabase.myDao().getMovies();

            if (movies.isEmpty()){
                Toast.makeText(this,"this is no favourite movie to show",Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    for (int i = 0; i < movies.size(); i++) {
                        MovieModel movieModel = new MovieModel();
                        movieModel.setId(movies.get(i).getId());
                        movieModel.setTitle(movies.get(i).getOriginal_title());
                        movieModel.setOverview(movies.get(i).getOverview());
                        movieModel.setPoster_path(movies.get(i).getPoster_path());
                        movieModel.setVote_average(movies.get(i).getVote_average());
                        movieModel.setRelease_date(movies.get(i).getRelease_date());

                        movieLists.add(movieModel);
                    }
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    Log.i("favourite error", e.getMessage().toString());
                }

                MyMoviesAdapter myMoviesAdapter = new MyMoviesAdapter(movieLists, context, filmType);
                recyclerView.setAdapter(myMoviesAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
