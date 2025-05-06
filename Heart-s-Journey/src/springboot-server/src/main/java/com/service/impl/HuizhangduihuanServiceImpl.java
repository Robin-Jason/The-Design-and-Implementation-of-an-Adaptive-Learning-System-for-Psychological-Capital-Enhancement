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


import com.dao.HuizhangduihuanDao;
import com.entity.HuizhangduihuanEntity;
import com.service.HuizhangduihuanService;
import com.entity.vo.HuizhangduihuanVO;
import com.entity.view.HuizhangduihuanView;

@Service("huizhangduihuanService")
public class HuizhangduihuanServiceImpl extends ServiceImpl<HuizhangduihuanDao, HuizhangduihuanEntity> implements HuizhangduihuanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<HuizhangduihuanEntity> page = this.selectPage(
                new Query<HuizhangduihuanEntity>(params).getPage(),
                new EntityWrapper<HuizhangduihuanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<HuizhangduihuanEntity> wrapper) {
		  Page<HuizhangduihuanView> page =new Query<HuizhangduihuanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<HuizhangduihuanVO> selectListVO(Wrapper<HuizhangduihuanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public HuizhangduihuanVO selectVO(Wrapper<HuizhangduihuanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<HuizhangduihuanView> selectListView(Wrapper<HuizhangduihuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public HuizhangduihuanView selectView(Wrapper<HuizhangduihuanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
