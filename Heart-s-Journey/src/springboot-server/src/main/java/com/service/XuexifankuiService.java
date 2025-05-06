package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XuexifankuiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XuexifankuiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XuexifankuiView;


/**
 * 学习反馈
 *
 * @author 
 * @email 
 * @date 2025-01-06 17:22:05
 */
public interface XuexifankuiService extends IService<XuexifankuiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XuexifankuiVO> selectListVO(Wrapper<XuexifankuiEntity> wrapper);
   	
   	XuexifankuiVO selectVO(@Param("ew") Wrapper<XuexifankuiEntity> wrapper);
   	
   	List<XuexifankuiView> selectListView(Wrapper<XuexifankuiEntity> wrapper);
   	
   	XuexifankuiView selectView(@Param("ew") Wrapper<XuexifankuiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XuexifankuiEntity> wrapper);

   	

}

