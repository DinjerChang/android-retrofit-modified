package edu.uci.swe264p.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MovieListActivity extends AppCompatActivity {

    static final String TAG = MovieListActivity.class.getSimpleName();
    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static Retrofit retrofit = null;
    final static String API_KEY = "5f7cb29d489bbbb86ca6e6d90f422eb2";

    private List<Movie> topRatedMovies;

    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // set up recycler view
        recyclerView = findViewById(R.id.rvMovieList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // retrofit to fetch api and get topRatedMovies
       if(retrofit == null){
           retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<TopRatedResponse> call = movieApiService.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<TopRatedResponse>() {
            // asynchronous request
            @Override
            public void onResponse(Call<TopRatedResponse> call, Response<TopRatedResponse> response) {
                TopRatedResponse topRatedResponse = response.body();
                assert topRatedResponse != null;
                topRatedMovies = topRatedResponse.getResults();
                // send the topRatedMovies data to adapter
                recyclerView.setAdapter(new MovieListAdapter(topRatedMovies));
            }
            @Override
            public void onFailure(Call<TopRatedResponse> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });


    }

}
