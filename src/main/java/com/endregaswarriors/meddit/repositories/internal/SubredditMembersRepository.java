package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.SubredditMember;
import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SubredditMembersRepository extends CrudRepository<SubredditMember, SubredditMemberPK> {
    @Query("SELECT count(members.subredditMemberPK.user_id) FROM SubredditMember members WHERE members.subredditMemberPK.subreddit_id = :id")
    Integer countSubredditMembers(@Param("id") Integer subreddit_id);
}
