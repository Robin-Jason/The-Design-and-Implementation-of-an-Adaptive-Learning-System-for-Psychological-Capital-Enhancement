package com.utils;

/**
* 类说明 : 基于用户收藏的协同过滤算法
*/

import com.service.ExamrecordService;
import java.util.*;
import java.util.stream.Collectors;

public class UserBasedCollaborativeFiltering {
    // userRatings 是一个嵌套的 Map，外层键是用户 ID，内层键是学习资源的 ID（refid），值是该用户对该资源的学习评分。
    // 若该用户已学习某个资源，则将评分增加 1.0，表示该资源对该用户的兴趣增加。如果是首次学习该资源，则初始化评分为 1.0。
    private Map<String, Map<String, Double>> userRatings;
    private Map<String, List<String>> itemUsers;
    private Map<String, Integer> userIndex;
    private Map<Integer, String> indexUser;
    private Long[][] sparseMatrix;
    public UserBasedCollaborativeFiltering(Map<String, Map<String, Double>> userRatings,ExamrecordService examrecordService) {
        this.userRatings = userRatings;
        this.itemUsers = new HashMap<>();

        this.userIndex = new HashMap<>();//辅助存储每一个用户的用户索引index映射:user->index
        this.indexUser = new HashMap<>();//辅助存储每一个索引index对应的用户映射:index->user

        // 构建物品-用户倒排表
        int keyIndex = 0;
        for (String user : userRatings.keySet()) {
            Map<String, Double> ratings = userRatings.get(user);
            for (String item : ratings.keySet()) {
                if (!itemUsers.containsKey(item)) {
                    itemUsers.put(item, new ArrayList<>());
                }
                itemUsers.get(item).add(user);
            }
          //用户ID与稀疏矩阵建立对应关系
            this.userIndex.put(user,keyIndex);
            this.indexUser.put(keyIndex,user);
            keyIndex++;
        }

        int N = userRatings.size();
        this.sparseMatrix=new Long[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
            	this.sparseMatrix[i][j]=(long)0;
        }
        for(String item : itemUsers.keySet()) {
        	List<String> userList = itemUsers.get(item);
        	for(String u1 : userList) {
        		for(String u2 : userList) {
        			if(u1.equals(u2)){
                        continue;
                    }
        			this.sparseMatrix[this.userIndex.get(u1)][this.userIndex.get(u2)]+=1;
        		}
        	}
        }

    }

    public double calculateSimilarity(String user1, String user2) {
        //计算用户之间的相似度
        Integer id1 = this.userIndex.get(user1);
        Integer id2 = this.userIndex.get(user2);
        if(id1==null || id2==null) return 0.0;
        // 用户收藏的余弦相似性
        double cosSimilarity = this.sparseMatrix[id1][id2]/Math.sqrt(userRatings.get(indexUser.get(id1)).size()*userRatings.get(indexUser.get(id2)).size());
        return cosSimilarity;
    }


    public List<String> recommendItems(String targetUser, int numRecommendations) {
//        System.out.println("目标用户是：" + targetUser);
        // 计算目标用户与其他用户的相似度
        Map<String, Double> userSimilarities = new HashMap<>();
        for (String user : userRatings.keySet()) {
            if (!user.equals(targetUser)) {
//                System.out.println("当前被用于计算的用户是：" + user);
                double similarity = calculateSimilarity(targetUser, user);
                userSimilarities.put(user, similarity);
            }
        }

        // 根据相似度进行排序
        List<Map.Entry<String, Double>> sortedSimilarities = new ArrayList<>(userSimilarities.entrySet());
        sortedSimilarities.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 选择相似度最高的K个用户
        List<String> similarUsers = new ArrayList<>();
        for (int i = 0; i < numRecommendations; i++) {
            if (i < sortedSimilarities.size()) {
                similarUsers.add(sortedSimilarities.get(i).getKey());
            } else {
                break;
            }
        }

        // 获取相似用户喜欢的物品，并进行推荐
        Map<String, Double> recommendations = new HashMap<>();
        for (String user : similarUsers) {
            Map<String, Double> ratings = userRatings.get(user);
            for (String item : ratings.keySet()) {
                if (userRatings.get(targetUser)!=null && !userRatings.get(targetUser).containsKey(item)) {
                    recommendations.put(item, ratings.get(item));
                }
            }
        }

        // 排序推荐物品
        LinkedHashMap<String, Double> sortedRecommendations = new LinkedHashMap<>(recommendations);
        // 取前N个推荐物品
        int numItems = Math.min(numRecommendations, sortedRecommendations.size());
        sortedRecommendations = sortedRecommendations.entrySet()
        .stream()
        .sorted((Map.Entry.<String, Double>comparingByValue().reversed())).limit(numItems)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<String> result = new ArrayList<String>();
        result.addAll(sortedRecommendations.keySet());
        return result;
    }
}


