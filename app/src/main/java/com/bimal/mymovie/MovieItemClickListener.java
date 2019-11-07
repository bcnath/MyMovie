package com.bimal.mymovie;

import android.widget.ImageView;
import com.bimal.mymovie.Movie;

public interface MovieItemClickListener {
    void  onMovieClick(Movie movie, ImageView movieImageView);

}
