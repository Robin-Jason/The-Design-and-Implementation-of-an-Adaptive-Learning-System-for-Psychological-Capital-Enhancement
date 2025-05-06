package com.entity.vo;

import com.entity.XuexiziyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 学习资源
 * @author 
 * @email 
 * @date 2025-01-06 17:22:05
 */
public class XuexiziyuanVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 资源图片
	 */
	
	private String ziyuantupian;
		
	/**
	 * 资源类型
	 */
	
	private String ziyuanleixing;
		
	/**
	 * 学习积分
	 */
	
	private Double jifen;
		
	/**
	 * 学习视频
	 */
	
	private String xuexishipin;
		
	/**
	 * 资源文件
	 */
	
	private String ziyuanwenjian;
		
	/**
	 * 资源内容
	 */
	
	private String ziyuanneirong;
		
	/**
	 * 发布时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fabushijian;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
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
	 * 设置：资源类型
	 */
	 
	public void setZiyuanleixing(String ziyuanleixing) {
		this.ziyuanleixing = ziyuanleixing;
	}
	
	/**
	 * 获取：资源类型
	 */
	public String getZiyuanleixing() {
		return ziyuanleixing;
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
	 * 设置：学习视频
	 */
	 
	public void setXuexishipin(String xuexishipin) {
		this.xuexishipin = xuexishipin;
	}
	
	/**
	 * 获取：学习视频
	 */
	public String getXuexishipin() {
		return xuexishipin;
	}
				
	
	/**
	 * 设置：资源文件
	 */
	 
	public void setZiyuanwenjian(String ziyuanwenjian) {
		this.ziyuanwenjian = ziyuanwenjian;
	}
	
	/**
	 * 获取：资源文件
	 */
	public String getZiyuanwenjian() {
		return ziyuanwenjian;
	}
				
	
	/**
	 * 设置：资源内容
	 */
	 
	public void setZiyuanneirong(String ziyuanneirong) {
		this.ziyuanneirong = ziyuanneirong;
	}
	
	/**
	 * 获取：资源内容
	 */
	public String getZiyuanneirong() {
		return ziyuanneirong;
	}
				
	
	/**
	 * 设置：发布时间
	 */
	 
	public void setFabushijian(Date fabushijian) {
		this.fabushijian = fabushijian;
	}
	
	/**
	 * 获取：发布时间
	 */
	public Date getFabushijian() {
		return fabushijian;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
