package com.example.movieapp.trailers;

import java.util.ArrayList;

public class ModelTrailers {
    private ArrayList<TrailersModel> results;

    public ModelTrailers(ArrayList<TrailersModel> results) {
        this.results = results;
    }

    public ArrayList<TrailersModel> getTrailers() {
        return results;
    }

}
