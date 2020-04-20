package com.example.movieapp.trailers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;

import java.util.ArrayList;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.FilmItemRowHolder> {

    ArrayList<TrailersModel> trialers;
    Context mcontext;

    public TrailersAdapter(Context mcontext, ArrayList<TrailersModel> TrailersItem) {
        this.mcontext = mcontext;
        this.trialers = TrailersItem;
    }

    @NonNull
    @Override
    public FilmItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_row, null);
        TrailersAdapter.FilmItemRowHolder holder = new TrailersAdapter.FilmItemRowHolder(view);
        return holder;}

    @Override
    public void onBindViewHolder(@NonNull FilmItemRowHolder holder, int position) {
        int number = position + 1;
        holder.trailerNum.setText("Trailer" + number);
    }

    @Override
    public int getItemCount() {
        return trialers.size();
    }

    public class FilmItemRowHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView trailerNum;
        public FilmItemRowHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.image);
            this.trailerNum = itemView.findViewById(R.id.txtTrailer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(BuildTrailerUrl(trialers.get(getAdapterPosition()).getKey()));
                    intent.setData(uri);
                    mcontext.startActivity(intent);
                }
            });
        }
    }
    public static String BuildTrailerUrl(String trailerKey) {
        Uri uri = Uri.parse("https://www.youtube.com/watch").buildUpon().appendQueryParameter("v", trailerKey).build();
        Log.i("uri", uri.toString());
        return uri.toString();
    }
}
