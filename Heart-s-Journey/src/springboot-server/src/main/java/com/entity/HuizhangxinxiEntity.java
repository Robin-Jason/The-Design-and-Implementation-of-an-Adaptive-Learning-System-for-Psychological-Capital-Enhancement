package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 徽章信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
@TableName("huizhangxinxi")
public class HuizhangxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public HuizhangxinxiEntity() {
		
	}
	
	public HuizhangxinxiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 徽章名称
	 */
					
	private String huizhangmingcheng;
	
	/**
	 * 图片
	 */
					
	private String tupian;
	
	/**
	 * 兑换积分
	 */
					
	private Double jifen;
	
	/**
	 * 徽章简介
	 */
					
	private String huizhangjianjie;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：徽章名称
	 */
	public void setHuizhangmingcheng(String huizhangmingcheng) {
		this.huizhangmingcheng = huizhangmingcheng;
	}
	/**
	 * 获取：徽章名称
	 */
	public String getHuizhangmingcheng() {
		return huizhangmingcheng;
	}
	/**
	 * 设置：图片
	 */
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
	/**
	 * 设置：兑换积分
	 */
	public void setJifen(Double jifen) {
		this.jifen = jifen;
	}
	/**
	 * 获取：兑换积分
	 */
	public Double getJifen() {
		return jifen;
	}
	/**
	 * 设置：徽章简介
	 */
	public void setHuizhangjianjie(String huizhangjianjie) {
		this.huizhangjianjie = huizhangjianjie;
	}
	/**
	 * 获取：徽章简介
	 */
	public String getHuizhangjianjie() {
		return huizhangjianjie;
	}

}
