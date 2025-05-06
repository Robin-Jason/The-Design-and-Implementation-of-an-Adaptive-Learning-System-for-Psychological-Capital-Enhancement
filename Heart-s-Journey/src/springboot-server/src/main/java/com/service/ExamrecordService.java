package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ExamrecordEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ExamrecordVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ExamrecordView;


/**
 * 测试记录表
 *
 * @author 
 * @email 
 * @date 2025-01-06 17:22:07
 */
public interface ExamrecordService extends IService<ExamrecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ExamrecordVO> selectListVO(Wrapper<ExamrecordEntity> wrapper);
   	
   	ExamrecordVO selectVO(@Param("ew") Wrapper<ExamrecordEntity> wrapper);
   	
   	List<ExamrecordView> selectListView(Wrapper<ExamrecordEntity> wrapper);
   	
   	ExamrecordView selectView(@Param("ew") Wrapper<ExamrecordEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ExamrecordEntity> wrapper);

   	PageUtils queryPageGroupBy(Map<String, Object> params,Wrapper<ExamrecordEntity> wrapper);

    PageUtils queryPageOptionsNum(Map<String, Object> params,Wrapper<ExamrecordEntity> wrapper);

	// 获取四种心理资本得分
	// 获取指定用户的四个心理资本测试的最新得分总和
	List<ExamrecordVO> getLatestPsychCapitalScoresAndTotal(Long userId, List<String> papernames);
}

