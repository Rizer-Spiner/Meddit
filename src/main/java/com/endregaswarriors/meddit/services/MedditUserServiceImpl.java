package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.api.LoginUser;
import com.endregaswarriors.meddit.models.api.NewUser;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.api.UserTopMovies;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.SubredditMember;
import com.endregaswarriors.meddit.models.database.TopMovieList;
import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import com.endregaswarriors.meddit.repositories.internal.MedditUserRepository;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import com.endregaswarriors.meddit.repositories.internal.TopMovieListRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class MedditUserServiceImpl implements MedditUserService {

    private final MedditUserRepository medditUserRepository;
    private final SubredditMembersRepository membersRepository;
    private final TopMovieListRepository topMovieListRepository;

    public MedditUserServiceImpl(MedditUserRepository medditUserRepository, SubredditMembersRepository membersRepository, TopMovieListRepository topMovieListRepository) {
        this.medditUserRepository = medditUserRepository;
        this.membersRepository = membersRepository;
        this.topMovieListRepository = topMovieListRepository;
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

    @Override
    public CompletableFuture<Response<TopMovieList>> addFavoriteMovieSubreddit(UserTopMovies topMovies) {
        return CompletableFuture.supplyAsync(() -> {
            TopMovieList movieList = TopMovieList.builder()
                    .user_id(topMovies.getUser_id())
                    .build();

            Integer[] idArray = topMovies.getMovieIds();
            if(idArray.length > 0){
                switch (idArray.length) {
                    case 10:
                        movieList.setSubreddit10_id(idArray[9]);
                    case 9:
                        movieList.setSubreddit9_id(idArray[8]);
                    case 8:
                        movieList.setSubreddit8_id(idArray[7]);
                    case 7:
                        movieList.setSubreddit7_id(idArray[6]);
                    case 6:
                        movieList.setSubreddit6_id(idArray[5]);
                    case 5:
                        movieList.setSubreddit5_id(idArray[4]);
                    case 4:
                        movieList.setSubreddit4_id(idArray[3]);
                    case 3:
                        movieList.setSubreddit3_id(idArray[2]);
                    case 2:
                        movieList.setSubreddit2_id(idArray[1]);
                    case 1:
                        movieList.setSubreddit1_id(idArray[0]);
                }
                return new Response<>(Status.SUCCESS, topMovieListRepository.save(movieList));
            } else
                return new Response<>(Status.NO_CONTENT);
        });
    }

    @Override
    public CompletableFuture<Response<TopMovieList>> getFavoriteMovieSubreddits(Integer user_id) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<TopMovieList> optionalTopMovieList = topMovieListRepository.findById(user_id);
            return optionalTopMovieList.map(topMovieList -> new Response<>(Status.SUCCESS, topMovieList))
                    .orElseGet(() -> new Response<>(Status.INTERNAL_ERROR));
        });
    }
}
