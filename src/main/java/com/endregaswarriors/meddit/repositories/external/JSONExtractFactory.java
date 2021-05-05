package com.endregaswarriors.meddit.repositories.external;

import com.endregaswarriors.meddit.models.MovieDetails;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;


import java.io.IOException;

import java.time.LocalDate;


public class JSONExtractFactory {


    public static class TMDBMovieExtractor extends JsonDeserializer<MovieDetails> implements ApiRepository.CustomExtractor<MovieDetails> {

        @Override
        public MovieDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

            JsonNode node = jsonParser.getCodec().readTree(jsonParser);

            final String id = node.get("imdb_id").asText("-1");
            final String title = node.get("title").asText("null");
            final String synopsys = node.get("overview").asText("null");
            final String posterPath = node.get("poster_path").asText("null");
            final LocalDate releaseDate = LocalDate.parse(node.get("release_date").asText());
            final int runtime = node.get("runtime").asInt();
            final String status = node.get("status").asText("unknown");
            final double rating = node.get("vote_average").asDouble();


            return new MovieDetails(id, title, synopsys, posterPath, releaseDate, runtime,status, rating);
        }
    }
}
