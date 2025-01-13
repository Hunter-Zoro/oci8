package com.github.donghaichen.oci8.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.donghaichen.oci8.entity.JkBill;
import com.github.donghaichen.oci8.service.IJkBillService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author donghai
 * @since 2024-11-20
 */
@Controller
@RequestMapping("/jkBill")
public class JkBillController {
    @Resource
    private IJkBillService jkBillService;

    @GetMapping("/index")
    public ResponseEntity<List<JkBill>> index(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {

        QueryWrapper<JkBill> queryWrapper = new QueryWrapper<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String dateString = dateFormat.format(new Date());
//        queryWrapper.gt("modifydate", dateString);
//        queryWrapper.last(" limit 1000");
        List<JkBill> list = jkBillService.list(queryWrapper); // 使用 queryWrapper 来执行过滤

        return ResponseEntity.ok(list);  // 这里直接返回 PageInfo<JkStock>
    }
}
