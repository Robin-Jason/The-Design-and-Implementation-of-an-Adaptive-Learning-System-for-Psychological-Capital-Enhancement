package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;

import com.entity.ScoredeterminationEntity;
import com.entity.view.ScoredeterminationView;

import com.service.ScoredeterminationService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 分数判定
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
@RestController
@RequestMapping("/scoredetermination")
public class ScoredeterminationController {
    @Autowired
    private ScoredeterminationService scoredeterminationService;

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ScoredeterminationEntity scoredetermination,
		HttpServletRequest request){
        EntityWrapper<ScoredeterminationEntity> ew = new EntityWrapper<ScoredeterminationEntity>();
		PageUtils page = scoredeterminationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, scoredetermination), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ScoredeterminationEntity scoredetermination, 
		HttpServletRequest request){
        EntityWrapper<ScoredeterminationEntity> ew = new EntityWrapper<ScoredeterminationEntity>();
		PageUtils page = scoredeterminationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, scoredetermination), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ScoredeterminationEntity scoredetermination){
       	EntityWrapper<ScoredeterminationEntity> ew = new EntityWrapper<ScoredeterminationEntity>();
      	ew.allEq(MPUtil.allEQMapPre( scoredetermination, "scoredetermination")); 
        return R.ok().put("data", scoredeterminationService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ScoredeterminationEntity scoredetermination){
        EntityWrapper< ScoredeterminationEntity> ew = new EntityWrapper< ScoredeterminationEntity>();
 		ew.allEq(MPUtil.allEQMapPre( scoredetermination, "scoredetermination")); 
		ScoredeterminationView scoredeterminationView =  scoredeterminationService.selectView(ew);
		return R.ok("查询分数判定成功").put("data", scoredeterminationView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ScoredeterminationEntity scoredetermination = scoredeterminationService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(scoredetermination,deSens);
        return R.ok().put("data", scoredetermination);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ScoredeterminationEntity scoredetermination = scoredeterminationService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(scoredetermination,deSens);
        return R.ok().put("data", scoredetermination);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增分数判定") 
    public R save(@RequestBody ScoredeterminationEntity scoredetermination, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(scoredetermination);
        scoredeterminationService.insert(scoredetermination);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增分数判定")
    @RequestMapping("/add")
    public R add(@RequestBody ScoredeterminationEntity scoredetermination, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(scoredetermination);
        scoredeterminationService.insert(scoredetermination);
        return R.ok().put("data",scoredetermination.getId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改分数判定")
    public R update(@RequestBody ScoredeterminationEntity scoredetermination, HttpServletRequest request){
        //ValidatorUtils.validateEntity(scoredetermination);
        //全部更新
        scoredeterminationService.updateById(scoredetermination);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除分数判定")
    public R delete(@RequestBody Long[] ids){
        scoredeterminationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
