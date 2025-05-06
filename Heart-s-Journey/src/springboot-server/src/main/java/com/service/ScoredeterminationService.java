package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ScoredeterminationEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ScoredeterminationVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ScoredeterminationView;


/**
 * 分数判定
 *
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public interface ScoredeterminationService extends IService<ScoredeterminationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ScoredeterminationVO> selectListVO(Wrapper<ScoredeterminationEntity> wrapper);
   	
   	ScoredeterminationVO selectVO(@Param("ew") Wrapper<ScoredeterminationEntity> wrapper);
   	
   	List<ScoredeterminationView> selectListView(Wrapper<ScoredeterminationEntity> wrapper);
   	
   	ScoredeterminationView selectView(@Param("ew") Wrapper<ScoredeterminationEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ScoredeterminationEntity> wrapper);

   	

}

