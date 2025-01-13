package com.github.donghaichen.oci8.scheduled;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataSynchronizationJob {


    @Scheduled(cron = "0 0 */1 * * ?")
    public void run(){

    }
}
