package com.bimal.mymovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bimal.mymovie.homepage.Movie;
import com.bimal.mymovie.homepage.MovieItemClickListener;
import com.bimal.mymovie.R;
import com.bumptech.glide.Glide;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context context ;
    List<Movie> newlyadded;
    MovieItemClickListener movieItemClickListener;


    public MovieAdapter(Context c, List<Movie> m,MovieItemClickListener listener) {
        context = c;
        newlyadded = m;
        movieItemClickListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,viewGroup,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.MovieTitle.setText(newlyadded.get(i).getTitle());
        Glide.with(context)
                .load(newlyadded.get(i).getThumbnail())
                .into(myViewHolder.ImgMovie);



    }

    @Override
    public int getItemCount() {
        return newlyadded.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView MovieTitle;
        private ImageView ImgMovie;
        private ImageView CoverMovie;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            MovieTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);
            CoverMovie = itemView.findViewById(R.id.detail_movie_cover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    movieItemClickListener.onMovieClick(newlyadded.get(getAdapterPosition()),ImgMovie);


                }
            });

        }
    }
}
