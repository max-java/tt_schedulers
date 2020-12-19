package by.jrr.tt.schedulers.timer_executor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockCleaner {

    final Logger logger = LoggerFactory.getLogger(LockCleaner.class);

    public void cleanLocks() {
        logger.info("Locks are cleaned");
    }
}
