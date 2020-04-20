package com.example.movieapp.MoviesDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addMovie(Movie movie);

    @Query("select * from movies")
    public List<Movie> getMovies();

    //fun getMovies(): LiveData<ArrayList<Movie>>

    @Delete
    public void deleteMovie(Movie movie);

    @Update
    public void updateMovie(Movie movie);
}
