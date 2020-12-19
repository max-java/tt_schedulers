package by.jrr.tt.schedulers.timer_executor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class MyTimerTaskFactory {

    final Logger logger = LoggerFactory.getLogger("[timer]");

    public TimerTask getTimerJobWithMessage(final String message) {
        return new TimerTask() {
            @Override
            public void run() {
                logger.info("[timer] {}", message);
            }
        };
    }

    public TimerTask getTimerJob(final LockCleaner cleaner) {
        return new TimerTask() {
            @Override
            public void run() {
                cleaner.cleanLocks();
            }
        };
    }
}
