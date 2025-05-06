package com.dao;

import com.entity.ExamrecordEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ExamrecordVO;
import com.entity.view.ExamrecordView;


/**
 * 测试记录表
 * 
 * @author 
 * @email 
 * @date 2025-01-06 17:22:07
 */
public interface ExamrecordDao extends BaseMapper<ExamrecordEntity> {
	
	List<ExamrecordVO> selectListVO(@Param("ew") Wrapper<ExamrecordEntity> wrapper);
	
	ExamrecordVO selectVO(@Param("ew") Wrapper<ExamrecordEntity> wrapper);
	
	List<ExamrecordView> selectListView(@Param("ew") Wrapper<ExamrecordEntity> wrapper);

	List<ExamrecordView> selectListView(Pagination page,@Param("ew") Wrapper<ExamrecordEntity> wrapper);

	
	ExamrecordView selectView(@Param("ew") Wrapper<ExamrecordEntity> wrapper);
	
	List<ExamrecordView> selectGroupBy(Pagination page,@Param("ew") Wrapper<ExamrecordEntity> wrapper);

    List<ExamrecordView> selectOptionsNum(Pagination page,@Param("ew") Wrapper<ExamrecordEntity> wrapper);

	// 获取四种心理资本得分
	// 获取每个 papername 的最新得分总和
	List<ExamrecordVO> selectLatestScoresAndTotalByUserId(@Param("userId") Long userId, @Param("papernames") List<String> papernames);
}
