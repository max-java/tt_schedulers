package by.jrr.tt.schedulers.timer_executor.service;

import by.jrr.tt.schedulers.timer_executor.Taskable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceToTask implements Taskable {

    final ClientForServiceToTask clientForServiceToTask;

    public ServiceToTask(ClientForServiceToTask clientForServiceToTask) {
        this.clientForServiceToTask = clientForServiceToTask;
    }

    @Override
    public void toTimerTask() {
        printInfo();
    }

    public void printInfo() {
        clientForServiceToTask.printInfo();
    }
}
