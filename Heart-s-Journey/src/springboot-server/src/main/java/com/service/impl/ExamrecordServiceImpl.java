package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ExamrecordDao;
import com.entity.ExamrecordEntity;
import com.service.ExamrecordService;
import com.entity.vo.ExamrecordVO;
import com.entity.view.ExamrecordView;

@Service("examrecordService")
public class ExamrecordServiceImpl extends ServiceImpl<ExamrecordDao, ExamrecordEntity> implements ExamrecordService {
	
	@Override
	public PageUtils queryPageGroupBy(Map<String, Object> params, Wrapper<ExamrecordEntity> wrapper) {
		Page<ExamrecordView> page =new Query<ExamrecordView>(params).getPage();
        page.setRecords(baseMapper.selectGroupBy(page,wrapper));
    	PageUtils pageUtil = new PageUtils(page);
    	return pageUtil;
	}

    @Override
    public PageUtils queryPageOptionsNum(Map<String, Object> params, Wrapper<ExamrecordEntity> wrapper) {
        Page<ExamrecordView> page =new Query<ExamrecordView>(params).getPage();
        page.setRecords(baseMapper.selectOptionsNum(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ExamrecordEntity> page = this.selectPage(
                new Query<ExamrecordEntity>(params).getPage(),
                new EntityWrapper<ExamrecordEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ExamrecordEntity> wrapper) {
		  Page<ExamrecordView> page =new Query<ExamrecordView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ExamrecordVO> selectListVO(Wrapper<ExamrecordEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ExamrecordVO selectVO(Wrapper<ExamrecordEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ExamrecordView> selectListView(Wrapper<ExamrecordEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ExamrecordView selectView(Wrapper<ExamrecordEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

	// 获取四种心理资本得分
	@Autowired
	private ExamrecordDao examrecordDao;

	@Override
	public List<ExamrecordVO> getLatestPsychCapitalScoresAndTotal(Long userId, List<String> papernames) {
		// 调用 DAO 层查询每个 papername 的得分总和
		List<ExamrecordVO> list = examrecordDao.selectLatestScoresAndTotalByUserId(userId, papernames);
		// 调试，看看是否返回得分
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getPapername() + " " + list.get(i).getTotalScore());
//		}
		return list;
	}
}
