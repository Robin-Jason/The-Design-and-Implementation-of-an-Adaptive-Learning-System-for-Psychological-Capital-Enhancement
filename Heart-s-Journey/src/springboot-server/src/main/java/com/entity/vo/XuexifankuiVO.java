package com.entity.vo;

import com.entity.XuexifankuiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 学习反馈
 * @author 
 * @email 
 * @date 2025-01-06 17:22:05
 */
public class XuexifankuiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 资源图片
	 */
	
	private String ziyuantupian;
		
	/**
	 * 学习积分
	 */
	
	private Double jifen;
		
	/**
	 * 学习时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date xuexishijian;
		
	/**
	 * 反馈建议
	 */
	
	private String fankuijianyi;
		
	/**
	 * 用户账号
	 */
	
	private String yonghuzhanghao;
		
	/**
	 * 用户姓名
	 */
	
	private String yonghuxingming;
		
	/**
	 * 跨表用户id
	 */
	
	private Long crossuserid;
		
	/**
	 * 跨表主键id
	 */
	
	private Long crossrefid;
		
	/**
	 * 回复内容
	 */
	
	private String shhf;
				
	
	/**
	 * 设置：资源图片
	 */
	 
	public void setZiyuantupian(String ziyuantupian) {
		this.ziyuantupian = ziyuantupian;
	}
	
	/**
	 * 获取：资源图片
	 */
	public String getZiyuantupian() {
		return ziyuantupian;
	}
				
	
	/**
	 * 设置：学习积分
	 */
	 
	public void setJifen(Double jifen) {
		this.jifen = jifen;
	}
	
	/**
	 * 获取：学习积分
	 */
	public Double getJifen() {
		return jifen;
	}
				
	
	/**
	 * 设置：学习时间
	 */
	 
	public void setXuexishijian(Date xuexishijian) {
		this.xuexishijian = xuexishijian;
	}
	
	/**
	 * 获取：学习时间
	 */
	public Date getXuexishijian() {
		return xuexishijian;
	}
				
	
	/**
	 * 设置：反馈建议
	 */
	 
	public void setFankuijianyi(String fankuijianyi) {
		this.fankuijianyi = fankuijianyi;
	}
	
	/**
	 * 获取：反馈建议
	 */
	public String getFankuijianyi() {
		return fankuijianyi;
	}
				
	
	/**
	 * 设置：用户账号
	 */
	 
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
				
	
	/**
	 * 设置：用户姓名
	 */
	 
	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}
	
	/**
	 * 获取：用户姓名
	 */
	public String getYonghuxingming() {
		return yonghuxingming;
	}
				
	
	/**
	 * 设置：跨表用户id
	 */
	 
	public void setCrossuserid(Long crossuserid) {
		this.crossuserid = crossuserid;
	}
	
	/**
	 * 获取：跨表用户id
	 */
	public Long getCrossuserid() {
		return crossuserid;
	}
				
	
	/**
	 * 设置：跨表主键id
	 */
	 
	public void setCrossrefid(Long crossrefid) {
		this.crossrefid = crossrefid;
	}
	
	/**
	 * 获取：跨表主键id
	 */
	public Long getCrossrefid() {
		return crossrefid;
	}
				
	
	/**
	 * 设置：回复内容
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：回复内容
	 */
	public String getShhf() {
		return shhf;
	}
			
}
