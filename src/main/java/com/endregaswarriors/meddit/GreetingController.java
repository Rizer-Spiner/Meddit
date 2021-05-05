package com.endregaswarriors.meddit;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();
//
//    private ComponentInNeedofSomeComponents RedditComponent;
//    private ComponentInNeedofSomeComponents StatisticsComponent;
//
//    public GreetingController(ApplicationContext context)
//    {
//        RedditComponent = (ComponentInNeedofSomeComponents) context.getBean("controller", context, "reddit");
//        StatisticsComponent = (ComponentInNeedofSomeComponents) context.getBean("controller", context, "statistics");
//    }
//
//    @GetMapping("/greeting")
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
//    }
//
//   @GetMapping("/redditComponent")
//    public String initializeRedditComponent()
//   {
//       System.out.println(RedditComponent.getSomeComponents().toString());
//       return RedditComponent.getSomeComponents().toString();
//   }
//
//    @GetMapping("/statisticsComponent")
//    public String initializeStatisticsComponent()
//    {
//        System.out.println(StatisticsComponent.getSomeComponents().toString());
//        return StatisticsComponent.getSomeComponents().toString();
//    }
}