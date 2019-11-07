package com.bimal.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        //ini views
        iniViews();


    }

        //get the data
    void iniViews(){
        play_fab=findViewById(R.id.play_fab);


        String movieTitle=getIntent().getExtras().getString("title");
        int imageResourceId=getIntent().getExtras().getInt("imgURL");
        int imageCover=getIntent().getExtras().getInt("imgCover");
        MovieThumbnailImg=findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverImg=findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imageCover).into(MovieCoverImg);
        tv_title=findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        tv_description=findViewById(R.id.detail_movie_desc);
        //setup animation
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(getApplicationContext(),MoviePlayerActivity.class);
            startActivity(i);
    }
}