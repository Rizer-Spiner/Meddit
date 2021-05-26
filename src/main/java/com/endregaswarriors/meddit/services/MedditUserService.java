package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.LoginUser;
import com.endregaswarriors.meddit.models.NewUser;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.database.MedditUser;

import java.util.concurrent.CompletableFuture;

public interface MedditUserService {

    CompletableFuture<Response<MedditUser>> signUserIn(NewUser newUser);
    CompletableFuture<Response<MedditUser>> loginUser(LoginUser loginUser);
    CompletableFuture<Response<Void>> joinSubreddit(Integer subreddit_id, Integer user_id);
    CompletableFuture<Response<Void>> leaveSubreddit(Integer subreddit_id, Integer user_id);
//    CompletableFuture<Response<Void>> addFavoriteMovieSubreddit

}
