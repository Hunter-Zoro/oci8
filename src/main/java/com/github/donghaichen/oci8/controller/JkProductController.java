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
import com.github.donghaichen.oci8.service.IDataSynchronizationLogService;
import com.github.donghaichen.oci8.service.IJkProductService;
import com.github.donghaichen.oci8.service.IJkStockService;
import com.github.donghaichen.oci8.service.IStoreProductService;
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
    @Resource
    private IStoreProductService storeProductService;
    @Resource
    private IJkStockService jkStockService;
    @Resource
    private IDataSynchronizationLogService dataSynchronizationLogService;

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

    @GetMapping("/test")
    public ResponseEntity<String> getCount(){


        long stime = System.currentTimeMillis();
        //查询页
        int pageSize = 1000;
        Long currentPage = 1L;

        //总页数 默认为1，第一次查询后会被实际总页数替换
        Long pages = 1L;
//        queryWrapper.last(" limit 1000");
        //多条插入
        int insertSize = 1000;
        List<StoreProduct> insertList = new ArrayList<>();

        try {
            //获取最后一次的查询日期
            QueryWrapper<DataSynchronizationLog> qw = new QueryWrapper<>();
            qw.last(" limit 1");
            qw.eq("status",1);
            DataSynchronizationLog last = dataSynchronizationLogService.getOne(qw);

            //查询本地表已经同步的所有code
            QueryWrapper<StoreProduct> idQw = new QueryWrapper<>();
            idQw.select("code");
            idQw.isNotNull("code");
            List<StoreProduct> productList = storeProductService.list(idQw);
            List<String> proIdList = new ArrayList<>();
            if(productList.size()!=0){
                proIdList = productList.stream().filter(m->m.getCode()!=null).map(m->m.getCode()).toList();
            }


            QueryWrapper<JkProduct> jkProductQueryWrapper = new QueryWrapper<>();
            if (last != null) {
                jkProductQueryWrapper.ge("product.MODIFYDATE", DateUtil.parse(last.getDate(),"yyyy-MM-dd").toString());
            }
            do {
                //
                Page<JkProduct> jkPage = new Page<>(currentPage, pageSize);
                IPage<JkProduct> jkProductIPage = jkproductService.productList(jkPage, jkProductQueryWrapper);
                pages = jkProductIPage.getPages();


                int i = 0;
                //获取分页的数据
                List<JkProduct> records = jkProductIPage.getRecords();

                for (JkProduct jkProduct : records) {

                    String code = jkProduct.getCode();
                    //手动赋值
                    StoreProduct storeProduct = new StoreProduct();
                    storeProduct.setCode(code);
                    storeProduct.setStore_name(jkProduct.getName());
                    storeProduct.setPrice(jkProduct.getPrice());
                    storeProduct.setStock(jkProduct.getSlNum());
                    String s = JSONUtil.toJsonStr(jkProduct);
                    storeProduct.setExt(s);
                    //code存在做更新，否则做插入
                    if(proIdList.contains(code)){
                        UpdateWrapper<StoreProduct> updateWrapper = new UpdateWrapper();
                        updateWrapper.eq("code",code);
                        storeProductService.update(storeProduct,updateWrapper);

                    }else {
                        insertList.add(storeProduct);
                        i++;
                        if (i % insertSize == 0) {
                            storeProductService.list(new QueryWrapper<>());
                            storeProductService.saveOrUpdateBatch(insertList);
                            insertList.clear();
                            i = 0;
                        }
                    }
                }
                currentPage++;
            } while (currentPage <= pages);
        }catch (Exception e){
            DataSynchronizationLog dataSynchronizationLog = new DataSynchronizationLog();
            dataSynchronizationLog.setDate(DateUtil.now());
            dataSynchronizationLog.setStatus(false);
            dataSynchronizationLog.setInfo(e.getMessage());
            dataSynchronizationLogService.save(dataSynchronizationLog);
            return ResponseEntity.ok("失败："+e);

        }
        DataSynchronizationLog dataSynchronizationLog = new DataSynchronizationLog();
        dataSynchronizationLog.setDate(DateUtil.now());
        dataSynchronizationLog.setStatus(true);
        dataSynchronizationLog.setInfo("成功");
        dataSynchronizationLogService.save(dataSynchronizationLog);

        long etime = System.currentTimeMillis();
        System.out.printf("执行时长：%d 毫秒.", (etime - stime));
        return ResponseEntity.ok("成功");
    }
    @GetMapping("/getProductList")
    public ResponseEntity<String> getPorductList(){
       // List<JkProduct> jsList = jkproductService.productList();


        return ResponseEntity.ok("");
    }


}
