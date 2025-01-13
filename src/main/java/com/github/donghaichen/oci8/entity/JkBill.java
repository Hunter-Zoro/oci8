package com.github.donghaichen.oci8.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author donghai
 * @since 2024-11-20
 */
@TableName("SHLIB.JK_BILL")
@Data
//@ApiModel(value = "JkBill对象", description = "")
public class JkBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("SNO")
    private String sno;

    private Integer ord;

    private String billdate;

    private String jkId;

    private String code;

    private Integer num;

    private BigDecimal price;

    private BigDecimal rate;

    private BigDecimal amt;

    private String status;

    private String customer;

    private String remark;

    private String modifydate;


}
