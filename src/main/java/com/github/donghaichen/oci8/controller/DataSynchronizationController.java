package com.github.donghaichen.oci8.controller;

import com.github.donghaichen.oci8.service.IDataSynchronizationService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dataSynchronization")
public class DataSynchronizationController {
    @Resource
    private IDataSynchronizationService dataSynchronizationService;
    @GetMapping("/exec")
    public ResponseEntity<String> getCount(){
        Boolean result = dataSynchronizationService.dataSynchronization();
        if(result){
            return ResponseEntity.ok("成功");
        }else{
            return ResponseEntity.ok("失败");
        }

    }
}
