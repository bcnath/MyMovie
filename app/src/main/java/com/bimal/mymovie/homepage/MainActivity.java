package com.bimal.mymovie.homepage;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.bimal.mymovie.R;
import com.bimal.mymovie.adapters.MovieAdapter;
import com.bimal.mymovie.moviedetail.MovieDetailActivity;
import com.bimal.mymovie.slider.Slide;
import com.bimal.mymovie.slider.SliderPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    DatabaseReference reference;
    private List<Slide> lstSlides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRv;
    private List<Movie> lstMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //iniViews()
        sliderpager=findViewById(R.id.slider_pager);
        indicator=findViewById(R.id.indicator);



        //iniSlider();
        // prepare a list of slides ..
        lstSlides=new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.avatarcover,""));
        lstSlides.add(new Slide(R.drawable.darkphoneixslider,""));
        lstSlides.add(new Slide(R.drawable.harrypotterslider,""));
        lstSlides.add(new Slide(R.drawable.avatarslider,""));

        SliderPagerAdapter adapter=new SliderPagerAdapter(this, lstSlides);
        sliderpager.setAdapter(adapter);



        //setup time
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new sliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);



        //recyclerview initialize
        MoviesRv=findViewById(R.id.Rv_movies);
        MoviesRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        MoviesRv.setHasFixedSize(true);


        //moviesRvWeek=findViewById(R.id.rv_movies_week);
        //Recyclerview setup

        //iniPopularMovies();
        lstMovies = new ArrayList<>();

        //database connection
        reference=FirebaseDatabase.getInstance().getReference("newlyadded");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    String title = dataSnapshot1.getKey();
                    String desc = dataSnapshot1.child("desc").getValue(String.class);
                    String cover = dataSnapshot1.child("cover").getValue(String.class);
                    String thumbnail = dataSnapshot1.child("thumbnail").getValue(String.class);
                    String streamlink = dataSnapshot1.child("streamlink").getValue(String.class);
                    Movie movie = new Movie(title,desc,cover,thumbnail, streamlink);
                    lstMovies.add(movie);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Oopsss... Something went wrong",Toast.LENGTH_SHORT).show();

            }
        });
        MovieAdapter movieAdapter=new MovieAdapter(this, lstMovies,this);
        MoviesRv.setAdapter(movieAdapter);



    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        // here we send movie info to detail activity
        //also we ll create the transition animation between the activity

        Intent intent=new Intent(this, MovieDetailActivity.class);

        intent.putExtra("title",movie.getTitle());
        intent.putExtra("thumbnail",movie.getThumbnail());
        intent.putExtra("cover",movie.getCover());
        intent.putExtra("desc",movie.getDesc());


        ActivityOptions options= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,movieImageView,"sharedName");
        }
        startActivity(intent,options.toBundle());


        //Toast.makeText(this,"Item clicked:" + movie.getTitle(),Toast.LENGTH_LONG).show();
    }

    class sliderTimer extends TimerTask{
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1){
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                    {
                        sliderpager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
