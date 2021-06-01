package com.endregaswarriors.meddit.api;


import com.endregaswarriors.meddit.models.api.JoinSubreddit;
import com.endregaswarriors.meddit.models.api.LoginUser;
import com.endregaswarriors.meddit.models.api.NewUser;
import com.endregaswarriors.meddit.models.api.UserTopMovies;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.TopMovieList;
import com.endregaswarriors.meddit.services.MedditUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/meddit/user")
@Api(tags = {"user"})
public class UsersController extends ControllerBase {

    MedditUserService medditUserService;

    public UsersController(MedditUserService medditUserService) {
        this.medditUserService = medditUserService;
    }

    @ApiOperation(value = "Sign in a new user to the Meddit platform", response = MedditUser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = " User successfully created/sign in"),
            @ApiResponse(code = 409, message = "The user could not be created with provided credentials. User already exists"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping("")
    CompletableFuture<ResponseEntity<MedditUser>> signUserIn(@RequestParam String firebase_id,
                                                             @RequestParam String username) {
        NewUser newUser = NewUser.builder()
                .firebase_id(firebase_id)
                .username(username)
                .build();
        return medditUserService.signUserIn(newUser).thenCompose(this::map);
    }

    @ApiOperation(value = "Log in a user to the Meddit platform", response = MedditUser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = " User successfully logged in"),
            @ApiResponse(code = 404, message = " Wring credentials. User could not be found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("")
    CompletableFuture<ResponseEntity<MedditUser>> loginUser(@RequestParam String firebase_id,
                                                            @RequestParam String username) {
        LoginUser loginUser = LoginUser.builder()
                .firebase_id(firebase_id)
                .username(username)
                .build();
        return medditUserService.loginUser(loginUser).thenCompose(this::map);
    }

    @ApiOperation(value = "Join or leave a subreddit platform")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = " Successfully updated membership"),
            @ApiResponse(code = 404, message = "The subscription could not be found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PatchMapping("")
    CompletableFuture<ResponseEntity<Void>> joinOrLeaveSubreddit(@RequestParam Integer subredditId,
                                                                 @RequestParam Integer userId,
                                                                 @RequestParam boolean join) {
        JoinSubreddit joinSubreddit = JoinSubreddit.builder()
                .subredditId(subredditId)
                .userId(userId)
                .join(join).build();
        if (joinSubreddit.isJoin())
            return medditUserService.joinSubreddit(joinSubreddit.getSubredditId(), joinSubreddit.getUserId()).thenCompose(this::map);
        else
            return medditUserService.leaveSubreddit(joinSubreddit.getSubredditId(), joinSubreddit.getUserId()).thenCompose(this::map);
    }

    @ApiOperation(value = "Save a users movie top list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated users' top movie list"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping("/top")
    CompletableFuture<ResponseEntity<TopMovieList>> saveTopList(UserTopMovies userTopMovies)
    {
        return medditUserService.addFavoriteMovieSubreddit(userTopMovies).thenCompose(this::map);
    }

    @ApiOperation(value = "Get users movie top list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received users' top movie list"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/top")
    CompletableFuture<ResponseEntity<TopMovieList>> getTopList(@RequestParam Integer user_id)
    {
        return medditUserService.getFavoriteMovieSubreddits(user_id).thenCompose(this::map);
    }

}
