package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.LoginUser;
import com.endregaswarriors.meddit.models.NewUser;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.repositories.internal.MedditUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class MedditUserServiceImpl implements MedditUserService {

    private MedditUserRepository medditUserRepository;

    public MedditUserServiceImpl(MedditUserRepository medditUserRepository) {
        this.medditUserRepository = medditUserRepository;
    }

    @Override
    public CompletableFuture<Response<MedditUser>> signUserIn(NewUser newUser) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<MedditUser> optionalMedditUser = medditUserRepository.login(newUser.getFirebase_id(), newUser.getUsername());
            if(optionalMedditUser.isPresent())
                return new Response<>(Status.CONFLICT);

            MedditUser userToSave = MedditUser.builder()
                    .firebase_id(newUser.getFirebase_id())
                    .username(newUser.getUsername())
                    .build();
            return new Response<>(Status.SUCCESS, medditUserRepository.save(userToSave));
        });
    }

    @Override
    public CompletableFuture<Response<MedditUser>> loginUser(LoginUser loginUser) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<MedditUser> optionalMedditUser = medditUserRepository.login(loginUser.getFirebase_id(), loginUser.getUsername());

            return optionalMedditUser.map(medditUser -> new Response<>(Status.SUCCESS, medditUser)).orElseGet(() -> new Response<>(Status.NOT_FOUND));
        });
    }

    @Override
    public CompletableFuture<Response<Void>> joinSubreddit(Long thread_id, Integer user_id) {
        return null;
    }

    @Override
    public CompletableFuture<Response<Void>> leaveSubreddit(Long thread_id, Integer user_id) {
        return null;
    }

    @Override
    public CompletableFuture<Response<Void>> upvoteThread(Long thread_id, Integer user_id, Boolean upvote) {
        return null;
    }

    @Override
    public CompletableFuture<Response<Void>> upvoteComment(Long comment_id, Integer user_id, Boolean upvote) {
        return null;
    }
}
