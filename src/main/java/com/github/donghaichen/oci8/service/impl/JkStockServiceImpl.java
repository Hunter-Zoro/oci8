package com.github.donghaichen.oci8.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.donghaichen.oci8.entity.JkStock;
import com.github.donghaichen.oci8.mapper.JkStockMapper;
import com.github.donghaichen.oci8.service.IJkStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class JkStockServiceImpl extends ServiceImpl<JkStockMapper, JkStock> implements IJkStockService {

}
