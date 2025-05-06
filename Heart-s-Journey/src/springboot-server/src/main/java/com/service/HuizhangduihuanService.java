package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.HuizhangduihuanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.HuizhangduihuanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.HuizhangduihuanView;


/**
 * 徽章兑换
 *
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
public interface HuizhangduihuanService extends IService<HuizhangduihuanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<HuizhangduihuanVO> selectListVO(Wrapper<HuizhangduihuanEntity> wrapper);
   	
   	HuizhangduihuanVO selectVO(@Param("ew") Wrapper<HuizhangduihuanEntity> wrapper);
   	
   	List<HuizhangduihuanView> selectListView(Wrapper<HuizhangduihuanEntity> wrapper);
   	
   	HuizhangduihuanView selectView(@Param("ew") Wrapper<HuizhangduihuanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<HuizhangduihuanEntity> wrapper);

   	

}

