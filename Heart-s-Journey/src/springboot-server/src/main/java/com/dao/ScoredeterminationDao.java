package com.dao;

import com.entity.ScoredeterminationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ScoredeterminationVO;
import com.entity.view.ScoredeterminationView;


/**
 * 分数判定
 * 
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public interface ScoredeterminationDao extends BaseMapper<ScoredeterminationEntity> {
	
	List<ScoredeterminationVO> selectListVO(@Param("ew") Wrapper<ScoredeterminationEntity> wrapper);
	
	ScoredeterminationVO selectVO(@Param("ew") Wrapper<ScoredeterminationEntity> wrapper);
	
	List<ScoredeterminationView> selectListView(@Param("ew") Wrapper<ScoredeterminationEntity> wrapper);

	List<ScoredeterminationView> selectListView(Pagination page,@Param("ew") Wrapper<ScoredeterminationEntity> wrapper);

	
	ScoredeterminationView selectView(@Param("ew") Wrapper<ScoredeterminationEntity> wrapper);
	

}
