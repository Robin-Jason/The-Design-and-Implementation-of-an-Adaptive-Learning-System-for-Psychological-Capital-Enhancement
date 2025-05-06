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


import com.dao.ScoredeterminationDao;
import com.entity.ScoredeterminationEntity;
import com.service.ScoredeterminationService;
import com.entity.vo.ScoredeterminationVO;
import com.entity.view.ScoredeterminationView;

@Service("scoredeterminationService")
public class ScoredeterminationServiceImpl extends ServiceImpl<ScoredeterminationDao, ScoredeterminationEntity> implements ScoredeterminationService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ScoredeterminationEntity> page = this.selectPage(
                new Query<ScoredeterminationEntity>(params).getPage(),
                new EntityWrapper<ScoredeterminationEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ScoredeterminationEntity> wrapper) {
		  Page<ScoredeterminationView> page =new Query<ScoredeterminationView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ScoredeterminationVO> selectListVO(Wrapper<ScoredeterminationEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ScoredeterminationVO selectVO(Wrapper<ScoredeterminationEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ScoredeterminationView> selectListView(Wrapper<ScoredeterminationEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ScoredeterminationView selectView(Wrapper<ScoredeterminationEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
