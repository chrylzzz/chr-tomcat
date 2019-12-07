package com.chryl.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * spring task
 * <p>
 * Created by Chryl on 2019/12/5.
 */
@Service
public class TaskService {


    //    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    @Scheduled(cron = "*/5 * * * * ?")
    public void firTask() {
        System.out.println("task 1 ...");
    }

    //    @Scheduled(initialDelay = 2000, fixedDelay = 3000)
    @Scheduled(cron = "0 22 17 * * ?")
    public void secTask() {
        System.out.println("task 2 ...");
    }
}
