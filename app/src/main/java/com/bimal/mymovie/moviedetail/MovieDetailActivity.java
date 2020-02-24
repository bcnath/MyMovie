package com.bimal.mymovie.moviedetail;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bimal.mymovie.R;
import com.bimal.mymovie.player.MoviePlayerActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

public class MovieDetailActivity extends AppCompatActivity implements SeriesItemClickListener {

    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView SeriesRv;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        String movieTitle=getIntent().getExtras().getString("title");
        tv_title=findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);



        String imageResourceId;
        imageResourceId=getIntent().getExtras().getString("thumbnail");
        MovieThumbnailImg=findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);


        String imageCover;
        imageCover=getIntent().getExtras().getString("cover");
        MovieCoverImg=findViewById(R.id.detail_movie_cover);
        Glide.with(MovieDetailActivity.this).load(imageCover).into(MovieCoverImg);


        String movieDesc=getIntent().getExtras().getString("desc");
        tv_description=findViewById(R.id.detail_movie_desc);
        tv_description.setText(movieDesc);
        //setup animation

        String movieLink;
        movieLink=getIntent().getExtras().getString("streamlink");
        play_fab=findViewById(R.id.play_fab);
        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MovieDetailActivity.this,MoviePlayerActivity.class);
                startActivity(i);

            }
        });
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));



    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSeriesClick(Series series, ImageView seriesImageView) {
        // here we send movie info to detail activity
        //also we ll create the transition animation between the activity

        Intent intent=new Intent(this, MoviePlayerActivity.class);

        intent.putExtra("Episodeno",series.getEpisodeno());
        intent.putExtra("Poster",series.getPoster());


        ActivityOptions options= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(MovieDetailActivity.this,seriesImageView,"sharedName");
        }
        startActivity(intent,options.toBundle());


        //Toast.makeText(this,"Item clicked:" + movie.getTitle(),Toast.LENGTH_LONG).show();
    }


}
