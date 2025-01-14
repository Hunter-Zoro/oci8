package com.github.donghaichen.oci8.controller;



import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.ObjectMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.donghaichen.oci8.entity.DataSynchronizationLog;
import com.github.donghaichen.oci8.entity.JkProduct;
import com.github.donghaichen.oci8.entity.JkStock;
import com.github.donghaichen.oci8.entity.StoreProduct;
import com.github.donghaichen.oci8.service.*;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@RequestMapping("/jkProduct")
public class JkProductController {
    @Resource
    private IJkProductService jkproductService;




    @GetMapping("/index")
    public ResponseEntity<List<JkProduct>> index(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {

        QueryWrapper<JkProduct> queryWrapper = new QueryWrapper<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String dateString = dateFormat.format(new Date());
//        queryWrapper.gt("modifydate", dateString);
//        queryWrapper.last(" limit 1000");
        List<JkProduct> list = jkproductService.list(queryWrapper); // 使用 queryWrapper 来执行过滤

        return ResponseEntity.ok(list);  // 这里直接返回 PageInfo<JkStock>
    }



}
