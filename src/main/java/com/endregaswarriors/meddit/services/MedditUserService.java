package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.api.LoginUser;
import com.endregaswarriors.meddit.models.api.NewUser;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.TopMovieList;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MedditUserService {

    CompletableFuture<Response<MedditUser>> signUserIn(NewUser newUser);
    CompletableFuture<Response<MedditUser>> loginUser(LoginUser loginUser);
    CompletableFuture<Response<Void>> joinSubreddit(Integer subreddit_id, Integer user_id);
    CompletableFuture<Response<Void>> leaveSubreddit(Integer subreddit_id, Integer user_id);
    CompletableFuture<Response<TopMovieList>> addFavoriteMovieSubreddit(Integer user_id, List<Integer> movieIds);

}
