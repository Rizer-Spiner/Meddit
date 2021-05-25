package com.endregaswarriors.meddit.api;

import com.endregaswarriors.meddit.models.MedditThread;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.api.AddThread;
import com.endregaswarriors.meddit.models.api.DeleteThread;
import com.endregaswarriors.meddit.models.api.GetThreads;
import com.endregaswarriors.meddit.models.api.VoteThread;
import com.endregaswarriors.meddit.models.database.Thread;
import com.endregaswarriors.meddit.services.ThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/meddit/thread")
@Api(tags = {"Threads"})
public class ThreadsController extends ControllerBase{

    private ThreadService threadService;

    public ThreadsController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @ApiOperation(value = "Create a new thread on a movie subreddit", response = MedditThread.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Thread successfully created"),
            @ApiResponse(code = 401, message = "The user is unauthorized to perform the action"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping("")
    public CompletableFuture<ResponseEntity<MedditThread>> createThread(@RequestBody AddThread newThread){
        return threadService.addThread(newThread).thenCompose(this::map);
    }

    @ApiOperation(value = "Delete a thread from a subreddit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thread successfully deleted"),
            @ApiResponse(code = 401, message = "The user is unauthorized to perform the action"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @DeleteMapping("")
    public CompletableFuture<ResponseEntity<Void>> deleteThread(@RequestBody DeleteThread deleteThread){
        return threadService.deleteThread(deleteThread).thenCompose(this::map);
    }

    @ApiOperation(value = "Get the threads for a subreddit", response = MedditThread[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Threads successfully received"),
            @ApiResponse(code = 204, message = "The query returned 0 results"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("")
    public CompletableFuture<ResponseEntity<List<MedditThread>>> getThreads(@RequestBody GetThreads getThreadsModel){
        return threadService.getSubredditThreads(getThreadsModel).thenCompose(this::map);
    }

    @ApiOperation(value = "Upvote or downVote a subreddit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thread successfully updated"),
            @ApiResponse(code = 401, message = "The user is unauthorized to perform the action"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PatchMapping("")
    public CompletableFuture<ResponseEntity<Void>> upvoteThread(@RequestBody VoteThread voteThread){
        return threadService.upVoteThread(voteThread).thenCompose(this::map);
    }
}
