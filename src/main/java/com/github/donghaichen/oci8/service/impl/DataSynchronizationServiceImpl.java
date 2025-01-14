package com.github.donghaichen.oci8.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.donghaichen.oci8.entity.DataSynchronizationLog;
import com.github.donghaichen.oci8.entity.JkProduct;
import com.github.donghaichen.oci8.entity.StoreProduct;
import com.github.donghaichen.oci8.service.*;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class DataSynchronizationServiceImpl implements IDataSynchronizationService {
    @Resource
    private IJkProductService jkproductService;
    @Resource
    private IStoreProductService storeProductService;

    @Resource
    private IDataSynchronizationLogService dataSynchronizationLogService;
    @Override
    public Boolean dataSynchronization() {
        Logger showTime = Logger.getLogger("showTime");
        List<String> storeInfoCol = Arrays.asList("isbn","pubno","barcode","author","pubdate","subname","csname","supplier","isme","attr");
        int allInsertCount = 0;
        int allUpdateCount = 0;
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

                    String storeInfo = "";
                    for (String col : storeInfoCol) {
                        Class<?> clazz = jkProduct.getClass();
                        // 获取指定名称的字段
                        Field field = clazz.getDeclaredField(col);
                        // 设置字段为可访问
                        field.setAccessible(true);
                        // 获取字段的值
                        String value = Convert.toStr(field.get(jkProduct)).trim();
                        if (!StringUtils.isEmpty(value)){
                            storeInfo+=col+":\""+value+"\",";
                        }
                    }
                    storeProduct.setStore_info(storeInfo);

                    //code存在做更新，否则做插入
                    if(proIdList.contains(code)){
                        UpdateWrapper<StoreProduct> updateWrapper = new UpdateWrapper();
                        updateWrapper.eq("code",code);
                        storeProductService.update(storeProduct,updateWrapper);
                        allUpdateCount++;

                    }else {
                        insertList.add(storeProduct);
                        i++;
                        if (i % insertSize == 0) {
                            storeProductService.list(new QueryWrapper<>());
                            storeProductService.saveOrUpdateBatch(insertList);
                            insertList.clear();
                            i = 0;
                        }
                        allInsertCount++;
                    }
                }
                currentPage++;
            } while (currentPage <= pages);
        }catch (Exception e){
            generateLog(false,"执行失败！已插入"+allInsertCount+"条，更新"+allUpdateCount+"条。报错信息："+e.getMessage());
            return false;

        }
        generateLog(true,"执行成功！插入"+allInsertCount+"条，更新"+allUpdateCount+"条");

        long etime = System.currentTimeMillis();
        showTime.info("执行时长："+ (etime - stime)+"毫秒");
        return true;
    }
    public void generateLog(Boolean status,String info){
        DataSynchronizationLog dataSynchronizationLog = new DataSynchronizationLog();
        dataSynchronizationLog.setDate(DateUtil.now());
        dataSynchronizationLog.setStatus(status);
        dataSynchronizationLog.setInfo(info);
        dataSynchronizationLogService.save(dataSynchronizationLog);
    }
}
