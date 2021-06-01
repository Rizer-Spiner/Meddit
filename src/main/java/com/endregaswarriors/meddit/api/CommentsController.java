package com.endregaswarriors.meddit.api;


import com.endregaswarriors.meddit.models.CommentSection;
import com.endregaswarriors.meddit.models.MedditComment;
import com.endregaswarriors.meddit.models.MedditThread;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import com.endregaswarriors.meddit.models.api.*;
import com.endregaswarriors.meddit.services.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/meddit/comments")
@Api(tags = {"Comments"})
public class CommentsController extends ControllerBase {

    CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }


    @ApiOperation(value = "Get the commentSection of a thread", response = MovieSearchResult[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comments successfully received"),
            @ApiResponse(code = 204, message = "The query returned 0 results"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("")
    CompletableFuture<ResponseEntity<CommentSection>> getCommentsForThread(@RequestParam Long threadId,
                                                                           @RequestParam Integer userId,
                                                                           @RequestParam int page) {
        GetComments getComments = GetComments.builder().threadId(threadId).userId(userId).page(page).build();
        return commentService.getCommentsForThread(getComments).thenCompose(this::map);
    }

    @ApiOperation(value = "Create a new comment for a movie thread", response = MedditThread.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Comment successfully created"),
            @ApiResponse(code = 401, message = "The user is unauthorized to perform the action"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping("")
    CompletableFuture<ResponseEntity<MedditComment>> addComment(@RequestParam Integer subredditId,
                                                                @RequestParam Long threadId,
                                                                @RequestParam Integer userId,
                                                                @RequestParam String content,
                                                                @RequestParam Long previousCommentId) {
        AddComment addComment = AddComment.builder().subredditId(subredditId).threadId(threadId)
                .userId(userId).content(content)
                .previousCommentId(previousCommentId).build();
        return commentService.addComment(addComment).thenCompose(this::map);
    }

    @ApiOperation(value = "Delete a comment from a subreddit thread")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comment successfully deleted"),
            @ApiResponse(code = 401, message = "The user is unauthorized to perform the action"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @DeleteMapping("")
    CompletableFuture<ResponseEntity<Void>> deleteComment(@RequestParam Long commentId,
                                                          @RequestParam Integer userId,
                                                          @RequestParam Integer subredditId) {
        DeleteComment deleteComment = DeleteComment.builder().commentId(commentId)
                .userId(userId).subredditId(subredditId).build();
        return commentService.deleteComment(deleteComment).thenCompose(this::map);
    }


    @ApiOperation(value = "Upvote or downVote a comment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comment successfully updated"),
            @ApiResponse(code = 401, message = "The user is unauthorized to perform the action"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PatchMapping("")
    CompletableFuture<ResponseEntity<Void>> upvoteComment(@RequestParam Integer subredditId,
                                                          @RequestParam Long commentId,
                                                          @RequestParam Integer userId,
                                                          @RequestParam Boolean upvote) {
        VoteComment voteComment = VoteComment.builder().subredditId(subredditId).commentId(commentId).userId(userId).upvote(upvote).build();
        return commentService.upvoteComment(voteComment).thenCompose(this::map);
    }
}
