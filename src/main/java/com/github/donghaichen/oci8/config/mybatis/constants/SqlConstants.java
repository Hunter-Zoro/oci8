package com.github.donghaichen.oci8.config.mybatis.constants;

import com.baomidou.mybatisplus.annotation.DbType;

public class SqlConstants {

    /**
     * 数据库的类型
     */
    public static DbType DB_TYPE;

    public static void init(DbType dbType) {
        DB_TYPE = dbType;
    }

}
