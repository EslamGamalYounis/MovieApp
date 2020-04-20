package com.example.movieapp.showMovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.MoviesDatabase.Movie;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MyMoviesAdapter extends RecyclerView.Adapter<MyMoviesAdapter.MoviewRowHolder> {
    private ArrayList<MovieModel> moviesDataList;
    Context context;
    String movieType;


    public MyMoviesAdapter(ArrayList<MovieModel> moviesDataList, Context context,String movieType) {
        this.moviesDataList = moviesDataList;
        this.context = context;
        this.movieType=movieType;
    }

    @NonNull
    @Override
    public MoviewRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_vedio,null);
        MoviewRowHolder moviewRowHolder=new MoviewRowHolder(view);
        return moviewRowHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewRowHolder holder, final int position) {
        //Log.i("ddddddddddd", moviesDataList.get(position).getPoster_path());
        Picasso.get().load("http://image.tmdb.org/t/p/w342/" + moviesDataList.get(position).getPoster_path()
        ).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detail = new Intent(context, DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("original_title", moviesDataList.get(position).getOriginal_title());
                    bundle.putString("overview", moviesDataList.get(position).getOverview());
                    bundle.putString("release_date", moviesDataList.get(position).getRelease_date());
                    bundle.putString("vote_average", moviesDataList.get(position).getVote_average());
                    bundle.putString("poster_path", moviesDataList.get(position).getPoster_path());
                    bundle.putString("id", String.valueOf(moviesDataList.get(position).getId()));
                    detail.putExtras(bundle);
                    startActivity(context, detail, bundle);
                }
            });

       /* holder.imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent detail = new Intent(context, DetailsActivity.class);
            Bundle bundle =new Bundle();
            bundle.putString("original_title", moviesDataList.get(position).getOriginal_title());
            bundle.putString("overview",moviesDataList.get(position).getOverview() );
            bundle.putString("release_date", moviesDataList.get(position).getRelease_date());
            bundle.putString("vote_average", moviesDataList.get(position).getVote_average());
            bundle.putString("poster_path",moviesDataList.get(position).getPoster_path() );
            bundle.putString("id", String.valueOf(moviesDataList.get(position).getId()));
            detail.putExtras(bundle);
            startActivity(context,detail,bundle);
        }
        });*/
    }

    @Override
    public int getItemCount() {
        return moviesDataList.size();
    }

    public class MoviewRowHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MoviewRowHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
