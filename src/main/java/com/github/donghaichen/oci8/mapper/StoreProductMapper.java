package com.github.donghaichen.oci8.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.donghaichen.oci8.config.mybatis.mapper.BaseMapperX;
import com.github.donghaichen.oci8.entity.StoreProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
@DS("slave2")
public interface StoreProductMapper extends BaseMapperX<StoreProduct> {


    //public List<StoreProduct> selectAll();
}
