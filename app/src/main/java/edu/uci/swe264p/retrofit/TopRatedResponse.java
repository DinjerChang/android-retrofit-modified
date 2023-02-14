package edu.uci.swe264p.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedResponse {
    /*
    observe JSON from the api
    page: 1
    results: xxx
    total_pages: 540
    total_results: 10797

    Only results is needed
     */

    @SerializedName("results")
    private List<Movie> results;


    TopRatedResponse(List<Movie> results){
        this.results = results;
    }
    // getter
    public List<Movie> getResults(){return results;}

}
