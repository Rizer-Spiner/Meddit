package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.api.LoginUser;
import com.endregaswarriors.meddit.models.api.NewUser;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.SubredditMember;
import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import com.endregaswarriors.meddit.repositories.internal.MedditUserRepository;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class MedditUserServiceImpl implements MedditUserService {

    private MedditUserRepository medditUserRepository;
    private SubredditMembersRepository membersRepository;

    public MedditUserServiceImpl(MedditUserRepository medditUserRepository, SubredditMembersRepository membersRepository) {
        this.medditUserRepository = medditUserRepository;
        this.membersRepository = membersRepository;
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
            return new Response<>(Status.CREATED, medditUserRepository.save(userToSave));
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
    public CompletableFuture<Response<Void>> joinSubreddit(Integer subreddit_id, Integer user_id) {
        return CompletableFuture.supplyAsync(() -> {
            SubredditMember newEntry = SubredditMember.builder()
                    .subredditMemberPK(SubredditMemberPK.builder().subreddit_id(subreddit_id).user_id(user_id).build())
                    .build();
            SubredditMember databaseEntry = membersRepository.save(newEntry);
            if(databaseEntry.getSubredditMemberPK() != null &&
                    databaseEntry.getSubredditMemberPK().getSubreddit_id() != null &&
                    databaseEntry.getSubredditMemberPK().getUser_id() != null)
                return new Response<>(Status.SUCCESS);
            else
                return new Response<>(Status.INTERNAL_ERROR);
        });
    }

    @Override
    public CompletableFuture<Response<Void>> leaveSubreddit(Integer subreddit_id, Integer user_id) {
        return CompletableFuture.supplyAsync(() -> {
            SubredditMemberPK id = SubredditMemberPK.builder().subreddit_id(subreddit_id).user_id(user_id).build();
            Optional<SubredditMember> databaseMember = membersRepository.findById(id);
            if(databaseMember.isEmpty())
                return new Response<>(Status.NOT_FOUND);
            membersRepository.delete(databaseMember.get());
            return new Response<>(Status.SUCCESS);
        });
    }
}
