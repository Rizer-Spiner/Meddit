package com.endregaswarriors.meddit;


import com.endregaswarriors.meddit.models.MedditThread;
import com.endregaswarriors.meddit.models.Response;

import com.endregaswarriors.meddit.models.api.GetThreads;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import com.endregaswarriors.meddit.repositories.internal.TheadLikesRepository;
import com.endregaswarriors.meddit.repositories.internal.ThreadRepository;
import com.endregaswarriors.meddit.services.ThreadService;
import com.endregaswarriors.meddit.services.ThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@SpringBootApplication
public class MedditApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedditApplication.class, args);

//        ThreadService service = new ThreadServiceImpl(threadRepository, threadLikeRepository ,subredditMembersRepository);
//
//        CompletableFuture<Response<List<MedditThread>>> sectionResponse = service.getSubredditThreads(GetThreads.builder()
//                .subredditId(1)
//                .userId(1)
//                .page(0).build());
//
//        while (!sectionResponse.isDone()) {
//            System.out.println("waiting....");
//        }
//
//        try {
//            System.out.println(sectionResponse.get().getStatus().name());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

    }


}
