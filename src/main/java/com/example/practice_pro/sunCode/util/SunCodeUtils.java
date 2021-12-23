package com.example.practice_pro.sunCode.util;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @ClassName sunCodeUtils
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/8 14:42
 * @Version 1.0
 */
public class SunCodeUtils {

    /**
     * @param appid
     * @param secret
     * @author hongguo.zhu
     * @Description 获取access_token
     * @Date 15:57 2021/12/8
     * @Param
     * @Return
     **/
    public static String GetUrlS(String appid, String secret) {
        String access_token = null;
        HttpGet httpGet = new HttpGet(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                        + appid + "&secret=" + secret);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse res = null;
        HttpEntity entity = null;
        try {
            res = httpClient.execute(httpGet);
            entity = res.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            JSONObject jsons = JSONObject.fromObject(result);
            String expires_in = jsons.getString("expires_in");
            //缓存
            if (Integer.parseInt(expires_in) == 7200) {
                //ok
                access_token = jsons.getString("access_token");
                System.out.println("access_token:" + access_token);
            } else {
                System.out.println("获取token失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return access_token;
    }

    public void createSunCode(String appid, String secret, HttpServletRequest request, HttpServletResponse response) {
        try {
            String access_token = GetUrlS(appid, secret);
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", "id=3");
            paramJson.put("page", "page");
            paramJson.put( "env_version","trial");
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
//            OutputStream os = new FileOutputStream(new File("C:/Users/Desktop/abc.png"));
            int len = 0;
            byte[] arr = new byte[1024];
            while ((len = inputStream.read(arr)) != -1) {
                outputStream.write(arr, 0, len);
                outputStream.flush();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
