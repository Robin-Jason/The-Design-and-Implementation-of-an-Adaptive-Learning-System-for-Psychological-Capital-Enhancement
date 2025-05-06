package com.dao;

import com.entity.XuexifankuiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XuexifankuiVO;
import com.entity.view.XuexifankuiView;


/**
 * 学习反馈
 * 
 * @author 
 * @email 
 * @date 2025-01-06 17:22:05
 */
public interface XuexifankuiDao extends BaseMapper<XuexifankuiEntity> {
	
	List<XuexifankuiVO> selectListVO(@Param("ew") Wrapper<XuexifankuiEntity> wrapper);
	
	XuexifankuiVO selectVO(@Param("ew") Wrapper<XuexifankuiEntity> wrapper);
	
	List<XuexifankuiView> selectListView(@Param("ew") Wrapper<XuexifankuiEntity> wrapper);

	List<XuexifankuiView> selectListView(Pagination page,@Param("ew") Wrapper<XuexifankuiEntity> wrapper);

	
	XuexifankuiView selectView(@Param("ew") Wrapper<XuexifankuiEntity> wrapper);
	

}
