package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ForumreportEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ForumreportVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ForumreportView;


/**
 * 互动社区举报
 *
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public interface ForumreportService extends IService<ForumreportEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ForumreportVO> selectListVO(Wrapper<ForumreportEntity> wrapper);
   	
   	ForumreportVO selectVO(@Param("ew") Wrapper<ForumreportEntity> wrapper);
   	
   	List<ForumreportView> selectListView(Wrapper<ForumreportEntity> wrapper);
   	
   	ForumreportView selectView(@Param("ew") Wrapper<ForumreportEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ForumreportEntity> wrapper);

   	

}

