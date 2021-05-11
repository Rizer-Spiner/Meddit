package com.endregaswarriors.meddit.repositories.external;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class TMDBContext implements ApiContext {

    final WebClient webClient;
    final WebClient.UriSpec<WebClient.RequestBodySpec> getSpec;

    public  TMDBContext() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 4000)
                .responseTimeout(Duration.ofMillis(4000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(4000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(4000, TimeUnit.MILLISECONDS)));


        this.webClient = WebClient.builder()
                .baseUrl(ApiConstants.TMDB_BASE_URL)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", ApiConstants.TMDB_BASE_URL))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
      this.getSpec = webClient.method(HttpMethod.GET);
        
    }

    @Override
    public  <TType> ResponseEntity<List<TType>> getMultiple(String pathParameters, String queryParameters, CustomExtractor<List<TType>> deserializer) {

        WebClient.ResponseSpec responseSpec;
        synchronized (this)
        {
            final String uri = !(queryParameters.equals(""))
                    ? String.format("/%s?%s", pathParameters, queryParameters)
                    : String.format("/%s", pathParameters);

            WebClient.RequestBodySpec bodySpec = getSpec.uri(uri);
            responseSpec = bodySpec.retrieve();

        }
        ResponseEntity<String> responseEntity = responseSpec.toEntity(String.class).block();

        if (responseEntity == null) return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String s = responseEntity.getBody();
            return new ResponseEntity<>(deserializer.deserialize(s), responseEntity.getStatusCode());
        } else return new ResponseEntity<>(new ArrayList<>(), responseEntity.getStatusCode());
    }

    @Override
    public  <TType> ResponseEntity<TType> get(String pathParameters, String queryParameters, CustomExtractor<TType> deserializer) {

        WebClient.ResponseSpec responseSpec;
        synchronized (this)
        {
            final String uri = !(queryParameters.equals(""))
                    ? String.format("/%s?%s", pathParameters, queryParameters)
                    : String.format("/%s", pathParameters);

            WebClient.RequestBodySpec bodySpec = getSpec.uri(uri);
            responseSpec = bodySpec.retrieve();
        }
        ResponseEntity<String> responseEntity = responseSpec.toEntity(String.class).block();

        if (responseEntity == null) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        if (responseEntity.getStatusCode().is2xxSuccessful())
        {
            String s = responseEntity.getBody();
            return new ResponseEntity<>(deserializer.deserialize(s), responseEntity.getStatusCode());
        } else return new ResponseEntity<>(null, responseEntity.getStatusCode());

    }
}
