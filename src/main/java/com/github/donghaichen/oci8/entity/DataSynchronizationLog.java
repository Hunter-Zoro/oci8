package com.github.donghaichen.oci8.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("eb_data_synchronization_log")
public class DataSynchronizationLog {
    public int id;
    public String date;
    public Boolean status;
    public String info;

}
