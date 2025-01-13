package com.github.donghaichen.oci8;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
        String username = "SHLIB";
        String password = "ksVfptnl3UoV";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("donghai") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() // 禁止打开输出目录
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.github.donghaichen.oci8") // 设置父包名
                                .moduleName("") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude("JK_Bill", "JK_PRODUCT", "JK_Stock") // 设置需要生成的表名
                                .addTablePrefix("t_", "c_") // 设置过滤表前缀
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
