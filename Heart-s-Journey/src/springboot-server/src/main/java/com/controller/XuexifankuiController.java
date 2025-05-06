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

import com.entity.XuexifankuiEntity;
import com.entity.view.XuexifankuiView;

import com.service.XuexifankuiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 学习反馈
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-06 17:22:05
 */
@RestController
@RequestMapping("/xuexifankui")
public class XuexifankuiController {
    @Autowired
    private XuexifankuiService xuexifankuiService;
    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XuexifankuiEntity xuexifankui,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			xuexifankui.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<XuexifankuiEntity> ew = new EntityWrapper<XuexifankuiEntity>();
		PageUtils page = xuexifankuiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexifankui), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XuexifankuiEntity xuexifankui, 
		HttpServletRequest request){
        EntityWrapper<XuexifankuiEntity> ew = new EntityWrapper<XuexifankuiEntity>();
		PageUtils page = xuexifankuiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexifankui), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XuexifankuiEntity xuexifankui){
       	EntityWrapper<XuexifankuiEntity> ew = new EntityWrapper<XuexifankuiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuexifankui, "xuexifankui")); 
        return R.ok().put("data", xuexifankuiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XuexifankuiEntity xuexifankui){
        EntityWrapper< XuexifankuiEntity> ew = new EntityWrapper< XuexifankuiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuexifankui, "xuexifankui")); 
		XuexifankuiView xuexifankuiView =  xuexifankuiService.selectView(ew);
		return R.ok("查询学习反馈成功").put("data", xuexifankuiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XuexifankuiEntity xuexifankui = xuexifankuiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(xuexifankui,deSens);
        return R.ok().put("data", xuexifankui);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XuexifankuiEntity xuexifankui = xuexifankuiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(xuexifankui,deSens);
        return R.ok().put("data", xuexifankui);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增学习反馈") 
    public R save(@RequestBody XuexifankuiEntity xuexifankui, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuexifankui);
        xuexifankuiService.insert(xuexifankui);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增学习反馈")
    @RequestMapping("/add")
    public R add(@RequestBody XuexifankuiEntity xuexifankui, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuexifankui);
        xuexifankuiService.insert(xuexifankui);
        return R.ok().put("data",xuexifankui.getId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改学习反馈")
    public R update(@RequestBody XuexifankuiEntity xuexifankui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexifankui);
        //全部更新
        xuexifankuiService.updateById(xuexifankui);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除学习反馈")
    public R delete(@RequestBody Long[] ids){
        xuexifankuiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
