package edu.uci.swe264p.retrofit;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<Movie> topRatedMovies;
    MovieListAdapter(List<Movie> topRatedMovies){
        this.topRatedMovies = topRatedMovies;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivMovie;
        TextView tvTitle;
        TextView tvReleaseDate;
        TextView tvVote;
        TextView tvOverview;

        // find the place to put data in movie_row.xml
        ViewHolder(View itemView){
            super(itemView);
            ivMovie = itemView.findViewById(R.id.ivMovie);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvVote = itemView.findViewById(R.id.tvVote);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }

    @Override
    // for creating a new ViewHolder with its corresponding layout
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    // for binding the data of single item to a view
    public void onBindViewHolder( ViewHolder holder, int position){
        Movie movie = topRatedMovies.get(position);
        // picasso lib to load image to ImageView
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()).into(holder.ivMovie);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvReleaseDate.setText(movie.getReleaseDate());
        holder.tvVote.setText(movie.getVoteAverage().toString());
        holder.tvOverview.setText(movie.getOverview());
    }
    @Override
    //for reporting the size of your dataset
    public int getItemCount(){return topRatedMovies.size();}
}
