package com.entity.view;

import com.entity.ScoredeterminationEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 分数判定
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
@TableName("scoredetermination")
public class ScoredeterminationView  extends ScoredeterminationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ScoredeterminationView(){
	}
 
 	public ScoredeterminationView(ScoredeterminationEntity scoredeterminationEntity){
 	try {
			BeanUtils.copyProperties(this, scoredeterminationEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
