package com.github.donghaichen.oci8.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.donghaichen.oci8.entity.JkStock;
import com.github.donghaichen.oci8.service.IJkStockService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author donghai
 * @since 2024-11-20
 */
@Controller
@RequestMapping("/jkStock")
public class JkStockController {

    @Resource
    private IJkStockService jkStockService;

    @GetMapping("/index")
    public ResponseEntity<List<JkStock>> index(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {

        QueryWrapper<JkStock> queryWrapper = new QueryWrapper<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String dateString = dateFormat.format(new Date());
//        queryWrapper.gt("modifydate", dateString);
//        queryWrapper.last(" limit 1000");
        List<JkStock> list = jkStockService.list(queryWrapper); // 使用 queryWrapper 来执行过滤

        return ResponseEntity.ok(list);  // 这里直接返回 PageInfo<JkStock>
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody JkStock jkStockDto) {
        JkStock jkStock = new JkStock();
        jkStock.setCode(jkStockDto.getCode());
        jkStock.setSlNum(jkStockDto.getSlNum());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jkStock.setModifydate(dateFormat.format(new Date()));

        boolean success = jkStockService.save(jkStock);
        return ResponseEntity.ok(success);  // 返回 HTTP 200 状态码和布尔值
    }

}
