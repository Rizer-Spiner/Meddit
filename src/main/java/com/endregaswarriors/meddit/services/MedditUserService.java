package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.api.LoginUser;
import com.endregaswarriors.meddit.models.api.NewUser;
import com.endregaswarriors.meddit.models.database.MedditUser;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface MedditUserService {

    CompletableFuture<Response<MedditUser>> signUserIn(NewUser newUser);
    CompletableFuture<Response<MedditUser>> loginUser(LoginUser loginUser);
    CompletableFuture<Response<Void>> joinSubreddit(Integer thread_id, Integer user_id);
    CompletableFuture<Response<Void>> leaveSubreddit(Integer thread_id, Integer user_id);
}
