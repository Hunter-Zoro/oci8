package com.github.donghaichen.oci8.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.donghaichen.oci8.entity.JkBill;
import com.github.donghaichen.oci8.mapper.JkBillMapper;
import com.github.donghaichen.oci8.service.IJkBillService;
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
public class JkBillServiceImpl extends ServiceImpl<JkBillMapper, JkBill> implements IJkBillService {

}
