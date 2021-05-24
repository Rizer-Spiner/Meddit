package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.api.*;
import com.endregaswarriors.meddit.models.MedditComment;
import com.endregaswarriors.meddit.models.Response;


import java.util.concurrent.CompletableFuture;

public interface CommentService {

    CompletableFuture<Response<CommentSection>> getCommentsForThread(GetComments getComments);
    CompletableFuture<Response<MedditComment>> addComment(AddComment addComment);
    CompletableFuture<Response<Void>> deleteComment(DeleteComment deleteComment);
    CompletableFuture<Response<Void>> upvoteComment(VoteComment voteComment);
}
