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


import com.dao.HuizhangxinxiDao;
import com.entity.HuizhangxinxiEntity;
import com.service.HuizhangxinxiService;
import com.entity.vo.HuizhangxinxiVO;
import com.entity.view.HuizhangxinxiView;

@Service("huizhangxinxiService")
public class HuizhangxinxiServiceImpl extends ServiceImpl<HuizhangxinxiDao, HuizhangxinxiEntity> implements HuizhangxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<HuizhangxinxiEntity> page = this.selectPage(
                new Query<HuizhangxinxiEntity>(params).getPage(),
                new EntityWrapper<HuizhangxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<HuizhangxinxiEntity> wrapper) {
		  Page<HuizhangxinxiView> page =new Query<HuizhangxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<HuizhangxinxiVO> selectListVO(Wrapper<HuizhangxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public HuizhangxinxiVO selectVO(Wrapper<HuizhangxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<HuizhangxinxiView> selectListView(Wrapper<HuizhangxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public HuizhangxinxiView selectView(Wrapper<HuizhangxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
