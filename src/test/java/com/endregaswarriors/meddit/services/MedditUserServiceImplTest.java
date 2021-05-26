package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.LoginUser;
import com.endregaswarriors.meddit.models.NewUser;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.SubredditMember;
import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import com.endregaswarriors.meddit.repositories.internal.MedditUserRepository;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class MedditUserServiceImplTest {

    @Mock
    MedditUserRepository medditUserRepository;
    @Mock
    SubredditMembersRepository membersRepository;

    MedditUserService medditUserService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        medditUserService = new MedditUserServiceImpl(medditUserRepository, membersRepository);
    }

    @Test
    public void signUserIn_userAlreadyInDatabase() throws ExecutionException, InterruptedException {
        MedditUser databaseUser = MedditUser.builder().user_id(1).firebase_id("123").username("user").build();
        Mockito.when(medditUserRepository.login(anyString(), anyString()))
                .thenReturn(Optional.of(databaseUser));

        CompletableFuture<Response<MedditUser>> responseCompletableFuture = medditUserService.signUserIn(
                NewUser.builder().username("username").firebase_id("123").build());
        Response<MedditUser> medditUserResponse = responseCompletableFuture.get();
        assertEquals(Status.CONFLICT, medditUserResponse.getStatus());
    }

    @Test
    public void signUserIn_savesNewUser() throws ExecutionException, InterruptedException {
        MedditUser user = MedditUser.builder().user_id(1).firebase_id("123").username("user").build();
        Mockito.when(medditUserRepository.login(anyString(), anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(medditUserRepository.save(any(MedditUser.class)))
                .thenReturn(user);

        CompletableFuture<Response<MedditUser>> responseCompletableFuture = medditUserService.signUserIn(
                NewUser.builder().username("user").firebase_id("123").build());
        Response<MedditUser> medditUserResponse = responseCompletableFuture.get();
        assertEquals(Status.SUCCESS, medditUserResponse.getStatus());
        assertEquals(user, medditUserResponse.getModel());
    }

    @Test
    public void loginUser_notFound() throws ExecutionException, InterruptedException {
        Mockito.when(medditUserRepository.login(anyString(), anyString()))
                .thenReturn(Optional.empty());

        CompletableFuture<Response<MedditUser>> responseCompletableFuture = medditUserService.loginUser(
                LoginUser.builder().firebase_id("123").username("username").build());
        Response<MedditUser> medditUserResponse = responseCompletableFuture.get();
        assertEquals(Status.NOT_FOUND, medditUserResponse.getStatus());
    }

    @Test
    public void loginUser_found() throws ExecutionException, InterruptedException {
        MedditUser user = MedditUser.builder().user_id(1).firebase_id("123").username("user").build();
        Mockito.when(medditUserRepository.login(anyString(), anyString()))
                .thenReturn(Optional.of(user));

        CompletableFuture<Response<MedditUser>> responseCompletableFuture = medditUserService.loginUser(
                LoginUser.builder().firebase_id("123").username("username").build());
        Response<MedditUser> medditUserResponse = responseCompletableFuture.get();
        assertEquals(Status.SUCCESS, medditUserResponse.getStatus());
        assertEquals(user, medditUserResponse.getModel());
    }

    @Test
    public void joinSubreddit_alreadyInsideDatabase() throws ExecutionException, InterruptedException {
        Mockito.when(membersRepository.save(any(SubredditMember.class)))
                .thenReturn(SubredditMember.builder().build());

        CompletableFuture<Response<Void>> responseCompletableFuture = medditUserService.joinSubreddit(1, 1);
        Response<Void> voidResponse = responseCompletableFuture.get();
        assertEquals(Status.INTERNAL_ERROR, voidResponse.getStatus());
    }

    @Test
    public void joinSubreddit_insertNewEntry() throws ExecutionException, InterruptedException {
        Mockito.when(membersRepository.save(any(SubredditMember.class)))
                .thenReturn(SubredditMember.builder()
                        .subredditMemberPK(SubredditMemberPK.builder().subreddit_id(1).user_id(1).build())
                        .build());

        CompletableFuture<Response<Void>> responseCompletableFuture = medditUserService.joinSubreddit(1, 1);
        Response<Void> voidResponse = responseCompletableFuture.get();
        assertEquals(Status.SUCCESS, voidResponse.getStatus());
    }

}