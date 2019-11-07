package com.bimal.mymovie.utils;

import com.bimal.mymovie.Movie;
import com.bimal.mymovie.R;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<Movie> getPopularMovies(){
        List<Movie> lstMovies=new ArrayList<>();
        lstMovies.add(new Movie("Avatar", R.drawable.avatar,R.drawable.avatarcover));
        lstMovies.add(new Movie("Dark Phoenix",R.drawable.darkphoenix,R.drawable.avatarcover));
        lstMovies.add(new Movie("Harry Potter",R.drawable.harrypotter,R.drawable.avatarcover));
        return lstMovies;
    }

    public static List<Movie> getWeekMovies(){
        List<Movie> lstMovies=new ArrayList<>();
        lstMovies.add(new Movie("Harry Potter",R.drawable.harrypotter,R.drawable.avatarcover));
        lstMovies.add(new Movie("Dark Phoenix",R.drawable.darkphoenix,R.drawable.avatarcover));
        lstMovies.add(new Movie("Avatar", R.drawable.avatar,R.drawable.avatarcover));

        return lstMovies;

    }
}
