package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.Subreddit;
import com.endregaswarriors.meddit.models.database.SubredditMember;
import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface SubredditMembersRepository extends CrudRepository<SubredditMember, SubredditMemberPK> {
    @Query("SELECT count(members.subredditMemberPK.user_id) FROM SubredditMember members " +
            "JOIN Subreddit subreddit ON members.subredditMemberPK.subreddit_id=subreddit.subreddit_id " +
            "WHERE subreddit.movie_id = :id")
    Integer countSubredditMembers(@Param("id") Integer IMDB_id);


}
