package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    @Query(" FROM Comment ct WHERE ct.thread_id=:threadID")
    Page<Comment> findAllByThread_id(@Param("threadID") Long threadId, Pageable page );
}
