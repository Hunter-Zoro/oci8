package com.github.donghaichen.oci8.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.donghaichen.oci8.config.mybatis.mapper.BaseMapperX;
import com.github.donghaichen.oci8.config.mybatis.query.LambdaQueryWrapperX;
import com.github.donghaichen.oci8.entity.JkProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author donghai
 * @since 2024-11-20
 */
@Mapper
@DS("master")
public interface JkProductMapper extends BaseMapperX<JkProduct> {

    default Long getCount(){
        return selectCount();
    }


   // @Select("SELECT product.*,stock.SL_NUM FROM SHLIB.JK_PRODUCT  product LEFT JOIN SHLIB.JK_STOCK stock ON product.CODE = stock.CODE")
    @Select(" SELECT product.*,stock.SL_NUM FROM SHLIB.JK_PRODUCT  product LEFT JOIN SHLIB.JK_STOCK stock ON product.CODE = stock.CODE ${ew.customSqlSegment}")
    public IPage<JkProduct> productList(IPage<JkProduct> page, @Param(Constants.WRAPPER)QueryWrapper queryWrapper);

}
