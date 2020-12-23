package by.jrr.tt.schedulers.timer_executor.config;

import by.jrr.tt.schedulers.timer_executor.service.LockCleaner;
import by.jrr.tt.schedulers.timer_executor.service.MyTimerTaskFactory;
import by.jrr.tt.schedulers.timer_executor.service.ServiceToTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.Instant;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class TimerConfig {

    final
    ServiceToTask serviceToTask;

    public TimerConfig(ServiceToTask serviceToTask) {
        this.serviceToTask = serviceToTask;
    }

    @Bean
    public Timer timer() {
        Timer timer = new Timer("myTimer");
        timer.schedule(
                jobFactory().getTimerJobWithMessage("withDelay"),
                5000L);
        timer.schedule(
                jobFactory().getTimerJobWithMessage("excacDate"),
                Date.from(Instant.now().plusSeconds(3)));
        timer.schedule(
                jobFactory().getTimerJobWithMessage("repeatable with delay"),
                0,
                1000);
        timer.scheduleAtFixedRate(
                jobFactory().getTimerJobWithMessage("repeatable with rate"),
                3000,
                4000);

        timer.scheduleAtFixedRate(
                jobFactory().getTimerJob(lockCleaner()),
                3000,
                4000);

        timer.scheduleAtFixedRate(
                jobFactory().wrapInTask(serviceToTask),
                3000,
                4000);

        return timer;
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(
                jobFactory().getTimerJobWithMessage("job with scheduledExecutorService"),
                2,
                1,
                TimeUnit.SECONDS);
        return executor;
    }

    @Bean
    public MyTimerTaskFactory jobFactory() {
        return new MyTimerTaskFactory();
    }

    @Bean
    LockCleaner lockCleaner() {
        return new LockCleaner();
    }
}
