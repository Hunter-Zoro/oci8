package com.github.donghaichen.oci8.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
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
@TableName("SHLIB.JK_STOCK")
@Data
//@ApiModel(value = "JkStock对象", description = "")
public class JkStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CODE")
    private String code;

    private Integer slNum;

    private String modifydate;


}
