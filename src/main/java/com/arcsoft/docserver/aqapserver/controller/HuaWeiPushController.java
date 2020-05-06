package com.arcsoft.docserver.aqapserver.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * @author xqh3622
 */
@RestController
@Slf4j
public class HuaWeiPushController {
    /** 用户在华为开发者联盟申请Push服务获取的服务参数 */
    private static  String appSecret = "87c41bd80895d11685e72a16ba8aeb116374b66ba3660acad21164d603d7d1ea";

    /** 用户在华为开发者联盟申请Push服务获取的服务参数 */
    private static  String appId = "101963145";

    /** 获取认证Token的URL */
    private static  String tokenUrl = "https://login.cloud.huawei.com/oauth2/v2/token";

    /** 应用级消息下发API */
    private static  String apiUrl = "https://api.push.hicloud.com/pushsend.do";

    /** 下发通知消息的认证Token */
    private static  String accessToken;

    /** accessToken的过期时间   */
    private static  long tokenExpiredTime;

    private void refreshToken() {
        //TODO 获取token
        try {
            String msgBody = MessageFormat.format("grant_type=client_credentials&client_secret={0}&client_id={1}", URLEncoder.encode(appSecret, "UTF-8"), appId);
            String response = HttpUtil.post(tokenUrl, msgBody);
            JSONObject obj = JSONObject.parseObject(response);
            accessToken = obj.getString("access_token");
            tokenExpiredTime = System.currentTimeMillis()+(obj.getLong("expires_in")-5 * 60)*1000;
        } catch (Exception e) {
            log.error("HvPush -- >> 认证Token获取失败！原因：e = {}", ExceptionUtil.getMessage(e));
        }
    }

    public void pushMessage() throws UnsupportedEncodingException {
        if(tokenExpiredTime <=System.currentTimeMillis())
        {
            refreshToken();
        }
        // 封装推送消息body，用于显示通知栏消息显示的标题和内容
        JSONObject body = new JSONObject();//仅通知栏消息需要设置标题和内容，透传消息key和value为用户自定义
        body.put("title","Push message title");//消息标题
        body.put("content","Push message content");//消息内容体

        // 封装消息点击动作的参数，“com.huawei.hms.hmsdemo”为推送消息中需要打开的应用APK包名。请根据实际包名来修改。
        JSONObject param = new JSONObject();
        param.put("appPkgName","com.huawei.hms.hmsdemo");//定义需要打开的appPkgName，这个参数在推送服务管理页面可以看到

        // 封装消息点击动作，用于定义通知栏点击行为
        JSONObject action = new JSONObject();
        action.put("type",3);//类型3为打开APP，其他行为请参考接口文档设置
        action.put("param",param);//消息点击动作参数

        // type为1时可以自定义行为，自定义行为需要app开发者事先给号参数
        // param.put("intent", "自定义行为，动作需要app开发者编辑好给你");

        // 封装消息类型，用于定义消息类型，区分是通知栏消息还是透传消息。
        JSONObject msg = new JSONObject();
        msg.put("type",3);//3: 通知栏消息，异步透传消息请根据接口文档设置
        msg.put("action",action);//消息点击动作
        msg.put("body",body);//通知栏消息body内容示例代码

        // 封装扩展消息，扩展消息中可以设置biTag用于消息打点，也可以携带customize参数用于触发通知栏点击事件的onEvent回调。
        JSONObject ext = new JSONObject();//扩展信息，含BI消息统计，特定展示风格，消息折叠。
        ext.put("biTag","Trump");//设置消息标签，如果带了这个标签，会在回执中推送给CP用于检测某种类型消息的到达率和状态
        JSONObject temp = new JSONObject();
        temp.put("season","Spring");
        temp.put("weather","raining");
        JSONArray customize = new JSONArray();
        customize.add(temp);
        ext.put("customize",customize);
        // ext扩展信息这个参数我并没理解到到底是干嘛的 ，在实际的开发中我只使用了ext.put("biTag", "Trump"); 并没有进行customize参数的携带

        // 最后将以上信息全部封装整个消息体
        JSONObject hps = new JSONObject();//华为PUSH消息总结构体
        hps.put("msg",msg);
        hps.put("ext",ext);
        JSONObject payload = new JSONObject();
        payload.put("hps",hps);

        // 创建一个数组json用于存放需要推送的设备id，一般是由前段传过来，或者数据库查询出来
        JSONArray deviceTokens = new JSONArray();//目标设备Token
       // deviceTokens.add(regId);
        deviceTokens.add("1586284772395664007601");

        // 封装整个http消息并发送
        String postBody = MessageFormat.format(
                "access_token={0}&nsp_svc={1}&nsp_ts={2}&device_token_list={3}&payload={4}",
                URLEncoder.encode(accessToken,"UTF-8"),
                URLEncoder.encode("openpush.message.api.send","UTF-8"),
                URLEncoder.encode(String.valueOf(System.currentTimeMillis() / 1000),"UTF-8"),
                URLEncoder.encode(deviceTokens.toString(),"UTF-8"),
                URLEncoder.encode(payload.toString(),"UTF-8"));

        String postUrl = apiUrl + "?nsp_ctx=" + URLEncoder.encode("{\"ver\":\"1\", \"appId\":\"" + appId + "\"}", "UTF-8");

        // 发送消息 这里我用的是hutool的HttpUtil工具类发的post请求
        String post = HttpUtil.post(postUrl, postBody);
        log.info("华为推送 -- >> 返回结果：" + post);

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        HuaWeiPushController h = new HuaWeiPushController();
//        h.refreshToken();
//        h.pushMessage();
        String s= "flower";

         int a = s.indexOf("b");
        System.out.println(a);
    }


}
