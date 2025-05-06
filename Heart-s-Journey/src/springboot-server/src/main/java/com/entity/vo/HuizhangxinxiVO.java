package com.entity.vo;

import com.entity.HuizhangxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 徽章信息
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public class HuizhangxinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
