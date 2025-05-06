package com.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.entity.vo.ExamrecordVO;
import com.service.ExamrecordService;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.XuexiziyuanEntity;
import com.entity.view.XuexiziyuanView;

import com.service.XuexiziyuanService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 学习资源
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-06 17:22:05
 */
@RestController
@RequestMapping("/xuexiziyuan")
public class XuexiziyuanController {
    @Autowired
    private XuexiziyuanService xuexiziyuanService;

    @Autowired
    private StoreupService storeupService;

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XuexiziyuanEntity xuexiziyuan,
		HttpServletRequest request){
        EntityWrapper<XuexiziyuanEntity> ew = new EntityWrapper<XuexiziyuanEntity>();
		PageUtils page = xuexiziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexiziyuan), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XuexiziyuanEntity xuexiziyuan, 
		HttpServletRequest request){
        EntityWrapper<XuexiziyuanEntity> ew = new EntityWrapper<XuexiziyuanEntity>();
		PageUtils page = xuexiziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexiziyuan), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XuexiziyuanEntity xuexiziyuan){
       	EntityWrapper<XuexiziyuanEntity> ew = new EntityWrapper<XuexiziyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuexiziyuan, "xuexiziyuan")); 
        return R.ok().put("data", xuexiziyuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XuexiziyuanEntity xuexiziyuan){
        EntityWrapper< XuexiziyuanEntity> ew = new EntityWrapper< XuexiziyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuexiziyuan, "xuexiziyuan")); 
		XuexiziyuanView xuexiziyuanView =  xuexiziyuanService.selectView(ew);
		return R.ok("查询学习资源成功").put("data", xuexiziyuanView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XuexiziyuanEntity xuexiziyuan = xuexiziyuanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(xuexiziyuan,deSens);
        return R.ok().put("data", xuexiziyuan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XuexiziyuanEntity xuexiziyuan = xuexiziyuanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(xuexiziyuan,deSens);
        return R.ok().put("data", xuexiziyuan);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增学习资源") 
    public R save(@RequestBody XuexiziyuanEntity xuexiziyuan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuexiziyuan);
        xuexiziyuanService.insert(xuexiziyuan);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增学习资源")
    @RequestMapping("/add")
    public R add(@RequestBody XuexiziyuanEntity xuexiziyuan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuexiziyuan);
        xuexiziyuanService.insert(xuexiziyuan);
        return R.ok().put("data",xuexiziyuan.getId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改学习资源")
    public R update(@RequestBody XuexiziyuanEntity xuexiziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexiziyuan);
        //全部更新
        xuexiziyuanService.updateById(xuexiziyuan);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除学习资源")
    public R delete(@RequestBody Long[] ids){
        xuexiziyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,XuexiziyuanEntity xuexiziyuan, HttpServletRequest request,String pre){
        EntityWrapper<XuexiziyuanEntity> ew = new EntityWrapper<XuexiziyuanEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = xuexiziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexiziyuan), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 基于用户收藏和测试得分推荐学习资源。
     * 使用协同过滤算法和心理资本得分来优化推荐。
     *
     * @param params 请求参数，包括可能的限制条件。
     * @param xuexiziyuan 学习资源实体，用于过滤。
     * @param request HTTP请求，用于获取会话中的用户ID。
     * @return 返回推荐的学习资源列表。
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,XuexiziyuanEntity xuexiziyuan, HttpServletRequest request){
        // 获取用户ID
        String userId = request.getSession().getAttribute("userId").toString();
        // 获取请求参数中的 limit 值，用于限制推荐的学习资源数量。如果没有提供 limit 参数，则默认为 10。
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        // 通过 StoreupService 查询所有的用户收藏记录，其中 type 为 1，表示收藏的资源类型是学习资源，tablename 为 xuexiziyuan，表示该收藏的是学习资源。
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("tablename", "xuexiziyuan"));
        Map<String, Map<String, Double>> ratings = new HashMap<>();

        // 获取用户的心理资本得分
        Map<String, Long> psychScores = getPsychCapitalScores(Long.parseLong(userId));
        for(String s : psychScores.keySet()) System.out.println(psychScores.get(s));    // 调试

        // 映射心理资本测试与学习资源
        Map<String, String> psychToResource = new HashMap<>();
        psychToResource.put("心理资本测评（乐观）", "乐观（Optimism）");
        psychToResource.put("心理资本测评（希望）", "希望（Hope）");
        psychToResource.put("心理资本测评（韧性）", "韧性（Resilience）");
        psychToResource.put("心理资本测评（自我效能）", "自我效能（Efficacy）");

        // 定义优先推荐的资源ID列表
        List<String> priorityResourceIds = new ArrayList<>();
        // 检查心理资本得分，对得分低于60的对应类型资源进行特殊处理
        for (Map.Entry<String, Long> entry : psychScores.entrySet()) {
            if (entry.getValue() < 60) {
                String resourceType = psychToResource.get(entry.getKey());
                List<XuexiziyuanEntity> resources = xuexiziyuanService.selectList(
                        new EntityWrapper<XuexiziyuanEntity>().eq("ziyuanleixing", resourceType)
                );
                // 收集得分低于60的资源
                for (XuexiziyuanEntity resource : resources) {
                    priorityResourceIds.add(resource.getId().toString());
                }
            }
        }

        // 将心理资本得分低于60的资源ID进行升序排序后加入最终推荐列表
        priorityResourceIds.sort(Comparator.comparing(id -> psychScores.get(psychToResource.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(xuexiziyuanService.selectById(Long.parseLong(id)).getZiyuanleixing()))
                .findFirst()
                .get().getKey())));

        // 将排序后的优先推荐资源ID列表添加到推荐列表中
        List<String> recommendations = new ArrayList<>(priorityResourceIds);


        // 构建用户评分矩阵进行收藏协同过滤：
        /*
        ratings 是一个嵌套的 Map，外层键是用户 ID，内层键是学习资源的 ID（refid），值是该用户对该资源的收藏评分。
	•	userRatings.put(storeup.getRefid().toString(), 1.0)：为每个用户的每个收藏的学习资源赋予评分。
	    若该用户已收藏某个资源，则将评分增加 1.0，表示该资源对该用户的兴趣增加。如果是首次收藏该资源，则初始化评分为 1.0。
         */
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity storeup : storeups) {
                Map<String, Double> userRatings = null; // 学习资源id和用户对该资源评分映射
                if(ratings.containsKey(storeup.getUserid().toString())) {
                    userRatings = ratings.get(storeup.getUserid().toString());
                } else {
                    userRatings = new HashMap<>();
                    ratings.put(storeup.getUserid().toString(), userRatings);
                }

                if(userRatings.containsKey(storeup.getRefid().toString())) {
                    userRatings.put(storeup.getRefid().toString(), userRatings.get(storeup.getRefid().toString())+1.0);
                } else {
                    userRatings.put(storeup.getRefid().toString(), 1.0);
                }
            }
        }

        // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings,examrecordService);

        // 加入指定用户推荐协同过滤后的物品
        String targetUser = userId;
        int numRecommendations = limit;
        recommendations.addAll(filter.recommendItems(targetUser, numRecommendations));

        // 输出推荐结果
        System.out.println("Recommendations for " + targetUser + ":");
        for (String item : recommendations) {
            System.out.println("推荐的结果为：" + item);
        }


        // 设置查询条件，按协同推荐顺序排序
        EntityWrapper<XuexiziyuanEntity> ew = new EntityWrapper<XuexiziyuanEntity>();
        ew.in("id", String.join(",", recommendations));
        if(recommendations!=null && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+"'"+String.join("','", recommendations)+"'"+")");
        }

        // 获取分页结果
        PageUtils page = xuexiziyuanService.queryPage(params, ew);
        List<XuexiziyuanEntity> pageList = (List<XuexiziyuanEntity>)page.getList();
        if(pageList.size()<limit) {
            // 如果结果不足，补充更多资源
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<XuexiziyuanEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(xuexiziyuanService.selectList(ew));
        } else if(pageList.size()>limit) {
            // 如果结果过多，截取
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }

    // 获取用户的四种心理资本测试得分
    @Autowired
    private ExamrecordService examrecordService;
    public Map<String, Long> getPsychCapitalScores(Long userId){
        // 定义四种心理资本测评的名称
        List<String> papernames = Arrays.asList("心理资本测评（乐观）", "心理资本测评（希望）", "心理资本测评（韧性）", "心理资本测评（自我效能）");

        // 获取每个 papername 对应的得分总和
        List<ExamrecordVO> examRecords = examrecordService.getLatestPsychCapitalScoresAndTotal(userId, papernames);

        // 创建结果映射
        Map<String, Long> scores = new HashMap<>();
        for (ExamrecordVO record : examRecords) {
            scores.put(record.getPapername(), record.getTotalScore()); // 使用 'myscore' 字段返回每个题目的得分
        }
        return scores;
    }
}
