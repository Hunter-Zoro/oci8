package com.github.donghaichen.oci8.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.donghaichen.oci8.entity.DataSynchronizationLog;
import com.github.donghaichen.oci8.mapper.DataSynchronizationLogMapper;
import com.github.donghaichen.oci8.service.IDataSynchronizationLogService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
@DS("slave2")
public class DataSynchronizationLogServiceImpl extends ServiceImpl<DataSynchronizationLogMapper, DataSynchronizationLog> implements IDataSynchronizationLogService {
}
