package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ForumtypeEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ForumtypeVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ForumtypeView;


/**
 * 互动社区类型
 *
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public interface ForumtypeService extends IService<ForumtypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ForumtypeVO> selectListVO(Wrapper<ForumtypeEntity> wrapper);
   	
   	ForumtypeVO selectVO(@Param("ew") Wrapper<ForumtypeEntity> wrapper);
   	
   	List<ForumtypeView> selectListView(Wrapper<ForumtypeEntity> wrapper);
   	
   	ForumtypeView selectView(@Param("ew") Wrapper<ForumtypeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ForumtypeEntity> wrapper);

   	

}

