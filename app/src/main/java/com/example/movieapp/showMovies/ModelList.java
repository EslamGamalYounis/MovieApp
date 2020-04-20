package com.example.movieapp.showMovies;

import java.util.ArrayList;

public class ModelList {

ArrayList<MovieModel>results;
    public ModelList(ArrayList<MovieModel> results) {
        this.results = results;
    }

    public ArrayList<MovieModel> getMovie() {
        return results;
    }
}
