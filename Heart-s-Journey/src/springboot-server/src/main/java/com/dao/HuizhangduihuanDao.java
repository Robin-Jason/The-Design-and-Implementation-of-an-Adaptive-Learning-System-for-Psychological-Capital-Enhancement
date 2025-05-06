package com.dao;

import com.entity.HuizhangduihuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.HuizhangduihuanVO;
import com.entity.view.HuizhangduihuanView;


/**
 * 徽章兑换
 * 
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public interface HuizhangduihuanDao extends BaseMapper<HuizhangduihuanEntity> {
	
	List<HuizhangduihuanVO> selectListVO(@Param("ew") Wrapper<HuizhangduihuanEntity> wrapper);
	
	HuizhangduihuanVO selectVO(@Param("ew") Wrapper<HuizhangduihuanEntity> wrapper);
	
	List<HuizhangduihuanView> selectListView(@Param("ew") Wrapper<HuizhangduihuanEntity> wrapper);

	List<HuizhangduihuanView> selectListView(Pagination page,@Param("ew") Wrapper<HuizhangduihuanEntity> wrapper);

	
	HuizhangduihuanView selectView(@Param("ew") Wrapper<HuizhangduihuanEntity> wrapper);
	

}
