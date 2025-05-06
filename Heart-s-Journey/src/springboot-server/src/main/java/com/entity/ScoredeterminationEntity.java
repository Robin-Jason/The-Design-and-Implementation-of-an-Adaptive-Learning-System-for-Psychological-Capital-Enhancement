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
 * 分数判定
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
@TableName("scoredetermination")
public class ScoredeterminationEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ScoredeterminationEntity() {
		
	}
	
	public ScoredeterminationEntity(T t) {
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
	 * 试卷名称
	 */
					
	private String papername;
	
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
	 * 设置：试卷名称
	 */
	public void setPapername(String papername) {
		this.papername = papername;
	}
	/**
	 * 获取：试卷名称
	 */
	public String getPapername() {
		return papername;
	}
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
