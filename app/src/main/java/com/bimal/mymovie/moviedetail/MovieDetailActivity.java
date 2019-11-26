package com.bimal.mymovie.moviedetail;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bimal.mymovie.R;
import com.bimal.mymovie.homepage.MainActivity;
import com.bimal.mymovie.homepage.Movie;
import com.bimal.mymovie.moviedetail.Series;
import com.bimal.mymovie.moviedetail.SeriesAdapter;
import com.bimal.mymovie.moviedetail.SeriesItemClickListener;
import com.bimal.mymovie.player.MoviePlayerActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        play_fab=findViewById(R.id.play_fab);


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
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));


        //Series RecyclerView
        SeriesRv=findViewById(R.id.Rv_series);
        List<Series> lstSeries=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference().child("season");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String episodeno = ds.getKey();
                    String poster = ds.child("poster").getValue(String.class);
                    String streamlink=ds.child("streamlink").getValue(String.class);
                    Series series=new Series(episodeno,poster,streamlink);
                    lstSeries.add(series);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MovieDetailActivity.this,"Oopsss... Something went wrong",Toast.LENGTH_SHORT).show();

            }
        });



        /*lstSeries.add(new Series(R.drawable.avatarslider,"Episode 1"));
        lstSeries.add(new Series(R.drawable.avatarslider,"Episode 1"));
        lstSeries.add(new Series(R.drawable.avatarslider,"Episode 1"));
        lstSeries.add(new Series(R.drawable.avatarslider,"Episode 1"));
        lstSeries.add(new Series(R.drawable.avatarslider,"Episode 1"));*/

        SeriesAdapter seriesAdapter=new SeriesAdapter(this,lstSeries,this);
        SeriesRv.setAdapter(seriesAdapter);
        SeriesRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));



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









    /*@Override
    public void onClick(View view) {

        Intent i = new Intent(this, MoviePlayerActivity.class);
            startActivity(i);
    }*/


}
