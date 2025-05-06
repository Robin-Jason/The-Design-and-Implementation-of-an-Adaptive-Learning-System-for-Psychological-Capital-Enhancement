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

import com.entity.ZiyuanleixingEntity;
import com.entity.view.ZiyuanleixingView;

import com.service.ZiyuanleixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 资源类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-06 17:22:05
 */
@RestController
@RequestMapping("/ziyuanleixing")
public class ZiyuanleixingController {
    @Autowired
    private ZiyuanleixingService ziyuanleixingService;

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZiyuanleixingEntity ziyuanleixing,
		HttpServletRequest request){
        EntityWrapper<ZiyuanleixingEntity> ew = new EntityWrapper<ZiyuanleixingEntity>();
		PageUtils page = ziyuanleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ziyuanleixing), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZiyuanleixingEntity ziyuanleixing, 
		HttpServletRequest request){
        EntityWrapper<ZiyuanleixingEntity> ew = new EntityWrapper<ZiyuanleixingEntity>();

		PageUtils page = ziyuanleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ziyuanleixing), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZiyuanleixingEntity ziyuanleixing){
       	EntityWrapper<ZiyuanleixingEntity> ew = new EntityWrapper<ZiyuanleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ziyuanleixing, "ziyuanleixing")); 
        return R.ok().put("data", ziyuanleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZiyuanleixingEntity ziyuanleixing){
        EntityWrapper< ZiyuanleixingEntity> ew = new EntityWrapper< ZiyuanleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ziyuanleixing, "ziyuanleixing")); 
		ZiyuanleixingView ziyuanleixingView =  ziyuanleixingService.selectView(ew);
		return R.ok("查询资源类型成功").put("data", ziyuanleixingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZiyuanleixingEntity ziyuanleixing = ziyuanleixingService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(ziyuanleixing,deSens);
        return R.ok().put("data", ziyuanleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZiyuanleixingEntity ziyuanleixing = ziyuanleixingService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(ziyuanleixing,deSens);
        return R.ok().put("data", ziyuanleixing);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增资源类型") 
    public R save(@RequestBody ZiyuanleixingEntity ziyuanleixing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(ziyuanleixing);
        ziyuanleixingService.insert(ziyuanleixing);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增资源类型")
    @RequestMapping("/add")
    public R add(@RequestBody ZiyuanleixingEntity ziyuanleixing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(ziyuanleixing);
        ziyuanleixingService.insert(ziyuanleixing);
        return R.ok().put("data",ziyuanleixing.getId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改资源类型")
    public R update(@RequestBody ZiyuanleixingEntity ziyuanleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ziyuanleixing);
        //全部更新
        ziyuanleixingService.updateById(ziyuanleixing);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除资源类型")
    public R delete(@RequestBody Long[] ids){
        ziyuanleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
