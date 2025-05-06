package com.utils;

import com.baidu.aip.client.BaseClient;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.util.AipClientConst;
import com.baidu.aip.util.Util;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.io.File;

// AipPicTrans类继承自BaseClient，用于实现图片的翻译功能
public class AipPicTrans extends BaseClient {
    // 构造函数，初始化基本的认证信息
    public AipPicTrans(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }

    // picTrans方法接收源语言、目标语言和图片文件路径，进行图片翻译
    public JSONObject picTrans(String from, String to, File imagePath) {
        AipRequest request = new AipRequest();
        this.preOperation(request); // 执行前置操作，如认证等

        // 检查是否有权限使用百度的高级API
        if (this.isBceKey.get()) {
            // 无权限时返回错误信息
            return Util.getGeneralError(AipClientConst.OPENAPI_NO_ACCESS_ERROR_CODE, "No permission to access data");
        } else {
            // 设置Unirest的请求超时设置为无限制
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = null;
            try {
                // 发送POST请求到百度图片翻译API
                response = Unirest.post("https://aip.baidubce.com/file/2.0/mt/pictrans/v1?access_token=" + accessToken)
                        .field("image", imagePath) // 图片文件
                        .field("from", from) // 源语言
                        .field("to", to)   // 目标语言
                        .field("v", "3")   // API版本
                        .asString();       // 响应类型为字符串
            } catch (UnirestException e) {
                // 处理请求异常
                throw new RuntimeException(e);
            }
            // 返回解析后的JSON对象
            return new JSONObject(response.getBody());
        }
    }
}