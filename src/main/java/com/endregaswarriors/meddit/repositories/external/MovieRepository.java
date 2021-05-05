package com.endregaswarriors.meddit.repositories.external;

import com.endregaswarriors.meddit.models.MovieDetails;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

import java.util.logging.Logger;

@Component
public class MovieRepository {


    public MovieRepository()
    {
    }

    public MovieDetails getMovie(String movieID)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.TMDB_BASE_URL)
//                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return null;
    }
}
