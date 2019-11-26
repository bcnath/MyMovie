package com.bimal.mymovie.moviedetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bimal.mymovie.R;
import com.bimal.mymovie.adapters.MovieAdapter;
import com.bimal.mymovie.homepage.MovieItemClickListener;
import com.bumptech.glide.Glide;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder> {
Context context;
List<Series> mData;
SeriesItemClickListener seriesItemClickListener;

    public SeriesAdapter(Context c, List<Series> s, MovieDetailActivity listener) {
        context = c;
        mData = s;
        seriesItemClickListener =listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.rv_series,viewGroup,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.SeriesTitle.setText(mData.get(i).getEpisodeno());
        //myViewHolder.ImgSeries.setImageResource(mData.get(i).getPoster());
        Glide.with(context)
                .load(mData.get(i).getPoster())
                .into(myViewHolder.ImgSeries);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView SeriesTitle;
        private ImageView ImgSeries;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            SeriesTitle = itemView.findViewById(R.id.item_series_title);
            ImgSeries = itemView.findViewById(R.id.item_series_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    seriesItemClickListener.onSeriesClick(mData.get(getAdapterPosition()),ImgSeries);


                }
            });

        }
    }




}
