package com.github.donghaichen.oci8.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.donghaichen.oci8.config.mybatis.mapper.BaseMapperX;
import com.github.donghaichen.oci8.entity.DataSynchronizationLog;
import org.apache.ibatis.annotations.Mapper;

@DS("slave2")
@Mapper
public interface DataSynchronizationLogMapper extends BaseMapperX<DataSynchronizationLog> {

}
