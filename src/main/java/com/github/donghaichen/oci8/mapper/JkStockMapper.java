package com.github.donghaichen.oci8.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.donghaichen.oci8.config.mybatis.mapper.BaseMapperX;
import com.github.donghaichen.oci8.entity.JkStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
public interface JkStockMapper extends BaseMapperX<JkStock> {
    default String getTableName() {

        return "jk_stock";
    }
}
