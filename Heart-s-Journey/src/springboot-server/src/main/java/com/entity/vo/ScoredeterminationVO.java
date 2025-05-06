package com.entity.vo;

import com.entity.ScoredeterminationEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 分数判定
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public class ScoredeterminationVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 最低分数
	 */
	
	private Integer score;
		
	/**
	 * 判定
	 */
	
	private String determine;
		
	/**
	 * 试卷id
	 */
	
	private String paperid;
		
	/**
	 * 分析
	 */
	
	private String analysis;
				
	
	/**
	 * 设置：最低分数
	 */
	 
	public void setScore(Integer score) {
		this.score = score;
	}
	
	/**
	 * 获取：最低分数
	 */
	public Integer getScore() {
		return score;
	}
				
	
	/**
	 * 设置：判定
	 */
	 
	public void setDetermine(String determine) {
		this.determine = determine;
	}
	
	/**
	 * 获取：判定
	 */
	public String getDetermine() {
		return determine;
	}
				
	
	/**
	 * 设置：试卷id
	 */
	 
	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}
	
	/**
	 * 获取：试卷id
	 */
	public String getPaperid() {
		return paperid;
	}
				
	
	/**
	 * 设置：分析
	 */
	 
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	
	/**
	 * 获取：分析
	 */
	public String getAnalysis() {
		return analysis;
	}
			
}
