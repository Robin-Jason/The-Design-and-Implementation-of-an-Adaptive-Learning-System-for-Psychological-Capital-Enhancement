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

import com.entity.HuizhangxinxiEntity;
import com.entity.view.HuizhangxinxiView;

import com.service.HuizhangxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 徽章信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
@RestController
@RequestMapping("/huizhangxinxi")
public class HuizhangxinxiController {
    @Autowired
    private HuizhangxinxiService huizhangxinxiService;

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,HuizhangxinxiEntity huizhangxinxi,
		HttpServletRequest request){
        EntityWrapper<HuizhangxinxiEntity> ew = new EntityWrapper<HuizhangxinxiEntity>();
		PageUtils page = huizhangxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huizhangxinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,HuizhangxinxiEntity huizhangxinxi, 
		HttpServletRequest request){
        EntityWrapper<HuizhangxinxiEntity> ew = new EntityWrapper<HuizhangxinxiEntity>();
		PageUtils page = huizhangxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huizhangxinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( HuizhangxinxiEntity huizhangxinxi){
       	EntityWrapper<HuizhangxinxiEntity> ew = new EntityWrapper<HuizhangxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( huizhangxinxi, "huizhangxinxi")); 
        return R.ok().put("data", huizhangxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(HuizhangxinxiEntity huizhangxinxi){
        EntityWrapper< HuizhangxinxiEntity> ew = new EntityWrapper< HuizhangxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( huizhangxinxi, "huizhangxinxi")); 
		HuizhangxinxiView huizhangxinxiView =  huizhangxinxiService.selectView(ew);
		return R.ok("查询徽章信息成功").put("data", huizhangxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        HuizhangxinxiEntity huizhangxinxi = huizhangxinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(huizhangxinxi,deSens);
        return R.ok().put("data", huizhangxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        HuizhangxinxiEntity huizhangxinxi = huizhangxinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(huizhangxinxi,deSens);
        return R.ok().put("data", huizhangxinxi);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增徽章信息") 
    public R save(@RequestBody HuizhangxinxiEntity huizhangxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(huizhangxinxi);
        huizhangxinxiService.insert(huizhangxinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增徽章信息")
    @RequestMapping("/add")
    public R add(@RequestBody HuizhangxinxiEntity huizhangxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(huizhangxinxi);
        huizhangxinxiService.insert(huizhangxinxi);
        return R.ok().put("data",huizhangxinxi.getId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改徽章信息")
    public R update(@RequestBody HuizhangxinxiEntity huizhangxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huizhangxinxi);
        //全部更新
        huizhangxinxiService.updateById(huizhangxinxi);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除徽章信息")
    public R delete(@RequestBody Long[] ids){
        huizhangxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
