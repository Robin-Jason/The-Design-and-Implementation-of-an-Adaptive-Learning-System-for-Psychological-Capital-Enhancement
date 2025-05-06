package com.dao;

import com.entity.HuizhangxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.HuizhangxinxiVO;
import com.entity.view.HuizhangxinxiView;


/**
 * 徽章信息
 * 
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public interface HuizhangxinxiDao extends BaseMapper<HuizhangxinxiEntity> {
	
	List<HuizhangxinxiVO> selectListVO(@Param("ew") Wrapper<HuizhangxinxiEntity> wrapper);
	
	HuizhangxinxiVO selectVO(@Param("ew") Wrapper<HuizhangxinxiEntity> wrapper);
	
	List<HuizhangxinxiView> selectListView(@Param("ew") Wrapper<HuizhangxinxiEntity> wrapper);

	List<HuizhangxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<HuizhangxinxiEntity> wrapper);

	
	HuizhangxinxiView selectView(@Param("ew") Wrapper<HuizhangxinxiEntity> wrapper);
	

}
