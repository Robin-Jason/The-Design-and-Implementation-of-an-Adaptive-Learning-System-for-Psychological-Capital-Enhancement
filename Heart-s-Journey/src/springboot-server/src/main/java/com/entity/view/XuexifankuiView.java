package com.entity.view;

import com.entity.XuexifankuiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 学习反馈
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-06 17:22:05
 */
@TableName("xuexifankui")
public class XuexifankuiView  extends XuexifankuiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public XuexifankuiView(){
	}
 
 	public XuexifankuiView(XuexifankuiEntity xuexifankuiEntity){
 	try {
			BeanUtils.copyProperties(this, xuexifankuiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
