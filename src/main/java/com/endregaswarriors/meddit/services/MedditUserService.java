package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.api.LoginUser;
import com.endregaswarriors.meddit.models.api.NewUser;
import com.endregaswarriors.meddit.models.database.MedditUser;

import java.util.concurrent.CompletableFuture;

public interface MedditUserService {

    CompletableFuture<Response<MedditUser>> signUserIn(NewUser newUser);
    CompletableFuture<Response<MedditUser>> loginUser(LoginUser loginUser);
    //joinSubreddit
    //leaveSubreddit
    //upvoteThread
    //upvoteComment
    //addFavoriteMovieSubreddit
}
