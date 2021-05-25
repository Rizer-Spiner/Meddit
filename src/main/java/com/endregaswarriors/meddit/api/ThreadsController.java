package com.endregaswarriors.meddit.api;

import com.endregaswarriors.meddit.models.AddThread;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.database.Thread;
import com.endregaswarriors.meddit.services.ThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/meddit/thread")
@Api(tags = {"Threads"})
public class ThreadsController extends ControllerBase{

    private ThreadService threadService;

    public ThreadsController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @ApiOperation(value = "Create a new thread on a movie subreddit", response = Thread.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Thread successfully created"),
            @ApiResponse(code = 404, message = "The subreddit by the id was not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping("")
    public CompletableFuture<ResponseEntity<Thread>> createThread(@RequestBody AddThread newThread){
        return threadService.addThread(newThread).thenComposeAsync(voidResponse -> {
            if(voidResponse.getStatus().equals(Status.SUCCESS))
                return custom(201);
            else
                return notFound();
        });
    }
}
