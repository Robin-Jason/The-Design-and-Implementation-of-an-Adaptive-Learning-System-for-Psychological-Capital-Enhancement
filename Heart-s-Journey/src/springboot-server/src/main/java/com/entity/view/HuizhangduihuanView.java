package com.entity.view;

import com.entity.HuizhangduihuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 徽章兑换
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
@TableName("huizhangduihuan")
public class HuizhangduihuanView  extends HuizhangduihuanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public HuizhangduihuanView(){
	}
 
 	public HuizhangduihuanView(HuizhangduihuanEntity huizhangduihuanEntity){
 	try {
			BeanUtils.copyProperties(this, huizhangduihuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
