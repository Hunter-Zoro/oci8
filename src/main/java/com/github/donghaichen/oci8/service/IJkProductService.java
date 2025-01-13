package com.github.donghaichen.oci8.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.donghaichen.oci8.entity.JkProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author donghai
 * @since 2024-11-20
 */

public interface IJkProductService extends IService<JkProduct> {



    public Long getCount();
    public IPage<JkProduct> productList(IPage<JkProduct> page, QueryWrapper queryWrapper);

}
