package com.endregaswarriors.meddit.repositories.external;

import com.endregaswarriors.meddit.models.MovieDetails;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomExtractorFactory {


    //// GsonBuilders
    private static final GsonBuilder movieDetailGsonBuilder = new GsonBuilder().registerTypeAdapter(MovieDetails.class,
            (JsonDeserializer<MovieDetails>) (json, typeOf, context) -> {

                String imdb_id = json.getAsJsonObject().get("imdb_id") == null
                        ? json.getAsJsonObject().get("id").getAsString()
                        : json.getAsJsonObject().get("imdb_id").getAsString();

                final String title = json.getAsJsonObject().get("title").getAsString();
                final String overview = json.getAsJsonObject().get("overview").getAsString();
                final String poster_path = String.format("%s%s", ApiConstants.TMDB_POSTER_API_BASE_URL,
                        json.getAsJsonObject().get("poster_path").getAsString());
                final LocalDate release_date = LocalDate.parse(json.getAsJsonObject().get("release_date").getAsString());
                final int runtime = json.getAsJsonObject().get("runtime").getAsInt();
                final String status = json.getAsJsonObject().get("status").getAsString();
                final double vote_average = json.getAsJsonObject().get("vote_average").getAsDouble();

                return new MovieDetails(imdb_id, title, overview, poster_path, release_date, runtime, status, vote_average);

            }).setDateFormat("yyyy-mm-dd");


    private static final GsonBuilder movieSearchGsonBuilder = new GsonBuilder().registerTypeAdapter(MovieSearchResult.class,
            (JsonDeserializer<MovieSearchResult>) (json, typeOf, context) -> {

                final String tmdb_id = json.getAsJsonObject().get("id").getAsString();
                final String title = json.getAsJsonObject().get("title").getAsString();
                final String overview = json.getAsJsonObject().get("overview").getAsString();
                final String poster_path = String.format("%s%s", ApiConstants.TMDB_POSTER_API_BASE_URL,
                        json.getAsJsonObject().get("poster_path").getAsString());
                final LocalDate release_date = LocalDate.parse(json.getAsJsonObject().get("release_date").getAsString());
                final double rating = json.getAsJsonObject().get("vote_average").getAsDouble();

                return new MovieSearchResult(tmdb_id, title, overview, release_date, rating, poster_path);

            }).setDateFormat("yyyy-mm-dd");





    //CustomExtractors
    private static final ApiContext.CustomExtractor<MovieDetails> movieDetailsExtractor = string -> {
        Gson gson = movieDetailGsonBuilder.create();
        return gson.fromJson(string, MovieDetails.class);
    };

    private static final ApiContext.CustomExtractor<MovieSearchResult> movieSearchExtractor = string -> {
        Gson gson = movieSearchGsonBuilder.create();
        return gson.fromJson(string, MovieSearchResult.class);
    };

    private static final ApiContext.CustomExtractor<List<MovieDetails>> movieDetailsListExtractor = string -> {

        Gson gson = movieDetailGsonBuilder.create();
        Type founderListType = new TypeToken<ArrayList<MovieDetails>>() {
        }.getType();

        JsonElement element = JsonParser.parseString(string);
        JsonElement list = element.getAsJsonObject().get("results").getAsJsonArray();

        return gson.fromJson(list, founderListType);
    };

    private static final ApiContext.CustomExtractor<List<MovieSearchResult>> movieSearchListExtractor = string -> {
        Gson gson = movieSearchGsonBuilder.create();

        Type founderListType = new TypeToken<ArrayList<MovieSearchResult>>() {
        }.getType();

        JsonElement element = JsonParser.parseString(string);
        JsonElement list = element.getAsJsonObject().get("results").getAsJsonArray();

        return gson.fromJson(list, founderListType);
    };






    ///Getters
    public static ApiContext.CustomExtractor<MovieDetails> getMovieDetailsExtractor() {
        return movieDetailsExtractor;
    }
    public static ApiContext.CustomExtractor<List<MovieDetails>> getMovieDetailsListExtractor() { return movieDetailsListExtractor; }

    public static ApiContext.CustomExtractor<MovieSearchResult> getMovieSearchExtractor(){return movieSearchExtractor;}
    public static ApiContext.CustomExtractor<List<MovieSearchResult>> getMovieSearchListExtractor(){return movieSearchListExtractor;}
}
