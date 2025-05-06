package com.utils;

import com.baidu.aip.client.BaseClient;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.http.EBodyFormat;
import com.baidu.aip.util.AipClientConst;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// AipTranslate 类继承自 BaseClient，用于实现文本翻译功能
public class AipTranslate extends BaseClient {
    // 构造函数，初始化基本的认证信息
    public AipTranslate(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }

    // textTrans 方法接收源语言代码、目标语言代码和待翻译文本，进行文本翻译
    public JSONObject textTrans(String from, String to, String q) {
        AipRequest request = new AipRequest();
        this.preOperation(request); // 执行前置操作，如认证等

        // 检查是否有权限使用百度的高级API
        if (this.isBceKey.get()) {
            // 无权限时返回错误信息
            return Util.getGeneralError(AipClientConst.OPENAPI_NO_ACCESS_ERROR_CODE, "No permission to access data");
        } else {
            // 在请求体中添加翻译的源语言、目标语言和文本
            request.addBody("from", from);
            request.addBody("to", to);
            request.addBody("q", q);

            // 设置访问令牌参数
            HashMap<String, String> params = new HashMap<>();
            params.put("access_token", this.accessToken);
            request.setParams(params);

            // 设置API的请求URI
            request.setUri("https://aip.baidubce.com/rpc/2.0/mt/texttrans/v1");

            // 设置请求体格式为原生JSON
            request.setBodyFormat(EBodyFormat.RAW_JSON);

            // 添加请求头，指定内容类型为JSON
            request.addHeader("Content-Type", "application/json");

            // 发送请求到服务器并返回响应结果
            return this.requestServer(request);
        }
    }
}