package com.github.donghaichen.oci8.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.donghaichen.oci8.entity.JkProduct;
import com.github.donghaichen.oci8.mapper.JkProductMapper;
import com.github.donghaichen.oci8.service.IJkProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author donghai
 * @since 2024-11-20
 */
@Service
@DS("master")
public class JkProductServiceImpl extends ServiceImpl<JkProductMapper, JkProduct> implements IJkProductService {

    @Override
    public Long getCount() {
        return this.getBaseMapper().getCount();
    }

    @Override
    public IPage<JkProduct> productList(IPage<JkProduct> page, QueryWrapper queryWrapper) {
        return this.getBaseMapper().productList(page,queryWrapper);
    }


}
