package by.jrr.tt.schedulers.timer_executor.service;

import by.jrr.tt.schedulers.timer_executor.Taskable;
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

    public TimerTask wrapInTask(final Taskable taskable) {
        return new TimerTask() {
            @Override
            public void run() {
                taskable.toTimerTask();
            }
        };
    }
}
