package by.jrr.tt.schedulers.spring_scheduler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class Scheduler {

    Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(fixedRate = 500)
    public void job() {
        logger.info("Current time {}", LocalDateTime.now());
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void cronJob() {
        logger.info("Cron Job every second {}", LocalDateTime.now());
    }

    @Schedules({
            @Scheduled(fixedDelay = 1200),
            @Scheduled(cron = "*/1 * * * * *")
    })
    public void delayWithCron() {
        logger.info("delayWithCron {}", LocalDateTime.now());
    }
}
