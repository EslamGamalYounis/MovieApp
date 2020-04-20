package com.example.movieapp.showMovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.MoviesDatabase.Movie;
import com.example.movieapp.R;
import com.example.movieapp.trailers.TrailersActivity;
import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {
    Context context;

    Button buFavourite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        context = this;
        Bundle bundle =getIntent().getExtras();

        final TextView movieMainTitle =findViewById(R.id.MovieMainTitle);
        ImageView imageIcon = findViewById(R.id.imageView3);
        TextView releaseDate =findViewById(R.id.date_of_release);
        TextView voteAverage =findViewById(R.id.vote_average);
        final TextView overview =findViewById(R.id.overview);
        buFavourite =findViewById(R.id.bu_favourite);

        Button trailersButton =findViewById(R.id.trailers_button);

        final String movieTitle =bundle.getString("original_title");
        final String poster =bundle.getString("poster_path");
        final String relaese =bundle.getString("release_date");
        final String voteavg =bundle.getString("vote_average");
        final String overviewText =bundle.getString("overview");
        final String id =bundle.getString("id");

        movieMainTitle.setText(movieTitle);
        releaseDate.setText(relaese);
        voteAverage.setText(voteavg);
        overview.setText(overviewText);

        Log.d("ggggggggg","http://image.tmdb.org/t/p/w342/"+poster);
        try{
            Picasso.get().load("http://image.tmdb.org/t/p/w342/"+poster).into(imageIcon);
        }catch (Exception e){
            e.printStackTrace();
            Log.d("ppppppppppppp",e.getMessage());
        }

        buFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie =new Movie();
                movie.setId(Integer.parseInt(id));
                movie.setOriginal_title(movieTitle);
                movie.setPoster_path(poster);
                movie.setRelease_date(relaese);
                movie.setOverview(overviewText);
                movie.setVote_average(voteavg);

                MainActivity.appDatabase.myDao().addMovie(movie);
                Toast.makeText(context,"movie added successfully to favourite",Toast.LENGTH_SHORT).show();
            }
        });


        trailersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TrailerId = new Intent(context, TrailersActivity.class);
                Bundle bundle =new Bundle();
                bundle.putString("id", id);
                TrailerId.putExtras(bundle);
                startActivity(TrailerId);
            }
        });


    }
}
