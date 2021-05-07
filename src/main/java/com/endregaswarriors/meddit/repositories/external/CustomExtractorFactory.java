package com.endregaswarriors.meddit.repositories.external;

import com.endregaswarriors.meddit.models.MovieDetails;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomExtractorFactory {

    private static final ApiContext.CustomExtractor<MovieDetails> movieDetailsExtractor = string -> {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()));

        Gson gson = builder.setDateFormat("yyyy-mm-dd").create();
        return gson.fromJson(string, MovieDetails.class);
    };

    private static final ApiContext.CustomExtractor<List<MovieDetails>> movieDetailsListExtractor = string -> {


        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()));

        Gson gson = builder.setDateFormat("yyyy-mm-dd").create();
        Type founderListType = new TypeToken<ArrayList<MovieDetails>>() {
        }.getType();

        JsonElement element = JsonParser.parseString(string);
        JsonElement list = element.getAsJsonObject().get("results").getAsJsonArray();

        return gson.fromJson(list, founderListType);
    };

    public static ApiContext.CustomExtractor<MovieDetails> getMovieDetailsExtractor() {
        return movieDetailsExtractor;
    }

    public static ApiContext.CustomExtractor<List<MovieDetails>> getMovieDetailsListExtractor() {
        return movieDetailsListExtractor;
    }
}
