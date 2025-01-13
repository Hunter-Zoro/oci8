package com.github.donghaichen.oci8.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.xiaoymin.knife4j.annotations.Ignore;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author donghai
 * @since 2024-11-20
 */
@TableName("SHLIB.JK_PRODUCT")
@Data
//@ApiModel(value = "JkProduct对象", description = "")
public class JkProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CODE")
    private String code;

    private String jkId;

    private String kind;

    private String name;

    private String isbn;

    private String pubno;

    private String barcode;

    private BigDecimal price;

    private String saletype;

    private String author;

    private String pubdate;

    private String subname;

    private String csname;

    private String supplier;

    @TableField("class")
    private String className;

    private String class2;

    private String isme;

    private String attr;

    private String modifydate;

    @TableField(exist = false)
    private int slNum;

}
