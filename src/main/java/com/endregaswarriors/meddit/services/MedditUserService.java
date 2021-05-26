package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.api.LoginUser;
import com.endregaswarriors.meddit.models.api.NewUser;
import com.endregaswarriors.meddit.models.database.MedditUser;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface MedditUserService {

    CompletableFuture<Response<MedditUser>> signUserIn(NewUser newUser);
    CompletableFuture<Response<MedditUser>> loginUser(LoginUser loginUser);
    CompletableFuture<Response<Void>> joinSubreddit(Long thread_id, Integer user_id);
    CompletableFuture<Response<Void>> leaveSubreddit(Long thread_id, Integer user_id);
    CompletableFuture<Response<Void>> upvoteThread(Long thread_id, Integer user_id, Boolean upvote);
    CompletableFuture<Response<Void>> upvoteComment(Long comment_id, Integer user_id, Boolean upvote);
}
