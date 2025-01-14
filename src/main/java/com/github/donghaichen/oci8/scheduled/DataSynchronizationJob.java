package com.github.donghaichen.oci8.scheduled;


import com.github.donghaichen.oci8.service.IDataSynchronizationService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
public class DataSynchronizationJob{

    @Resource
    private IDataSynchronizationService dataSynchronizationService;

    //整点每小时执行一次
    @Scheduled(cron = "0 0 * * * ?")
    public void run(){
        Boolean res = dataSynchronizationService.dataSynchronization();
        Logger log = Logger.getLogger("dataSynchronizationLog");
        if (res){
            log.info("数据同步定时任务执行成功");
        }else{
            log.info("数据同步定时任务执行失败");
        }

    }


}
