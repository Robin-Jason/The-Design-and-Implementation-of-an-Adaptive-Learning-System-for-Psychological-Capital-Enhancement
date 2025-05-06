package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.XuexifankuiDao;
import com.entity.XuexifankuiEntity;
import com.service.XuexifankuiService;
import com.entity.vo.XuexifankuiVO;
import com.entity.view.XuexifankuiView;

@Service("xuexifankuiService")
public class XuexifankuiServiceImpl extends ServiceImpl<XuexifankuiDao, XuexifankuiEntity> implements XuexifankuiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XuexifankuiEntity> page = this.selectPage(
                new Query<XuexifankuiEntity>(params).getPage(),
                new EntityWrapper<XuexifankuiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XuexifankuiEntity> wrapper) {
		  Page<XuexifankuiView> page =new Query<XuexifankuiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<XuexifankuiVO> selectListVO(Wrapper<XuexifankuiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XuexifankuiVO selectVO(Wrapper<XuexifankuiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XuexifankuiView> selectListView(Wrapper<XuexifankuiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XuexifankuiView selectView(Wrapper<XuexifankuiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
