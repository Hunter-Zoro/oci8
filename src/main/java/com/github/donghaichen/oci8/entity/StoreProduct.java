package com.github.donghaichen.oci8.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
@Data
@TableName("eb_store_product")
public class StoreProduct {

    @TableId("id")
    public int	id;
    public int	mer_id;
    public String	image;
    public String	recommend_image;
    public String	slider_image;
    public String	store_name;
    public String	store_info;
    public String	keyword;
    public String	bar_code;
    public String	cate_id;
    public BigDecimal price;
    public BigDecimal	vip_price;
    public BigDecimal	ot_price;
    public BigDecimal	postage;
    public String	unit_name;
    public short	sort;
    public int	sales;
    public int	stock;
    public Boolean	is_show;
    public Boolean	is_hot;
    public Boolean	is_benefit;
    public Boolean	is_best;
    public Boolean	is_new;
    public Boolean	is_virtual;
    public Boolean	virtual_type;
    public int	add_time;
    public Boolean	is_postage;
    public Boolean	is_del;
    public Boolean	mer_use;
    public int	give_integral;
    public BigDecimal	cost;
    public Boolean	is_seckill;
    public Boolean	is_bargain;
    public Boolean	is_good;
    public Boolean	is_sub;
    public Boolean	is_vip;
    public int	ficti;
    public int	browse;
    public String	code_path;
    public String	soure_link;
    public String	video_link;
    public int	temp_id;
    public Boolean	spec_type;
    public String	activity;
    public String	spu;
    public String	label_id;
    public String	command_word;
    public String	recommend_list;
    public Boolean	vip_product;
    public Boolean	presale;
    public int	presale_start_time;
    public int	presale_end_time;
    public int	presale_day;
    public String	logistics;
    public Boolean	freight;
    public String	custom_form;
    public Boolean	is_limit;
    public Boolean	limit_type;
    public int	limit_num;
    public int	min_qty;
    public String	ext;
    public String	code;










































































































































}
