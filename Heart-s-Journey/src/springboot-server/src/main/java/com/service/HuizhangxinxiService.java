package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.HuizhangxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.HuizhangxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.HuizhangxinxiView;


/**
 * 徽章信息
 *
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public interface HuizhangxinxiService extends IService<HuizhangxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<HuizhangxinxiVO> selectListVO(Wrapper<HuizhangxinxiEntity> wrapper);
   	
   	HuizhangxinxiVO selectVO(@Param("ew") Wrapper<HuizhangxinxiEntity> wrapper);
   	
   	List<HuizhangxinxiView> selectListView(Wrapper<HuizhangxinxiEntity> wrapper);
   	
   	HuizhangxinxiView selectView(@Param("ew") Wrapper<HuizhangxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<HuizhangxinxiEntity> wrapper);

   	

}

