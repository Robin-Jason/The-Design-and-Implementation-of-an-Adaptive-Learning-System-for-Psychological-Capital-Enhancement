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

import com.entity.HuizhangduihuanEntity;
import com.entity.view.HuizhangduihuanView;

import com.service.HuizhangduihuanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 徽章兑换
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-06 17:22:06
 */
@RestController
@RequestMapping("/huizhangduihuan")
public class HuizhangduihuanController {
    @Autowired
    private HuizhangduihuanService huizhangduihuanService;
    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,HuizhangduihuanEntity huizhangduihuan,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			huizhangduihuan.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<HuizhangduihuanEntity> ew = new EntityWrapper<HuizhangduihuanEntity>();
		PageUtils page = huizhangduihuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huizhangduihuan), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,HuizhangduihuanEntity huizhangduihuan, 
		HttpServletRequest request){
        EntityWrapper<HuizhangduihuanEntity> ew = new EntityWrapper<HuizhangduihuanEntity>();

		PageUtils page = huizhangduihuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huizhangduihuan), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( HuizhangduihuanEntity huizhangduihuan){
       	EntityWrapper<HuizhangduihuanEntity> ew = new EntityWrapper<HuizhangduihuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( huizhangduihuan, "huizhangduihuan")); 
        return R.ok().put("data", huizhangduihuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(HuizhangduihuanEntity huizhangduihuan){
        EntityWrapper< HuizhangduihuanEntity> ew = new EntityWrapper< HuizhangduihuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( huizhangduihuan, "huizhangduihuan")); 
		HuizhangduihuanView huizhangduihuanView =  huizhangduihuanService.selectView(ew);
		return R.ok("查询徽章兑换成功").put("data", huizhangduihuanView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        HuizhangduihuanEntity huizhangduihuan = huizhangduihuanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(huizhangduihuan,deSens);
        return R.ok().put("data", huizhangduihuan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        HuizhangduihuanEntity huizhangduihuan = huizhangduihuanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(huizhangduihuan,deSens);
        return R.ok().put("data", huizhangduihuan);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增徽章兑换") 
    public R save(@RequestBody HuizhangduihuanEntity huizhangduihuan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(huizhangduihuan);
        huizhangduihuanService.insert(huizhangduihuan);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增徽章兑换")
    @RequestMapping("/add")
    public R add(@RequestBody HuizhangduihuanEntity huizhangduihuan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(huizhangduihuan);
        huizhangduihuanService.insert(huizhangduihuan);
        return R.ok().put("data",huizhangduihuan.getId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改徽章兑换")
    public R update(@RequestBody HuizhangduihuanEntity huizhangduihuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huizhangduihuan);
        //全部更新
        huizhangduihuanService.updateById(huizhangduihuan);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除徽章兑换")
    public R delete(@RequestBody Long[] ids){
        huizhangduihuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
