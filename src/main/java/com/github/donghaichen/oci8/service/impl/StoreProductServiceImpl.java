package com.github.donghaichen.oci8.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.donghaichen.oci8.entity.StoreProduct;
import com.github.donghaichen.oci8.mapper.StoreProductMapper;
import com.github.donghaichen.oci8.service.IStoreProductService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@DS("slave2")
public class StoreProductServiceImpl extends ServiceImpl<StoreProductMapper, StoreProduct> implements IStoreProductService {

}
