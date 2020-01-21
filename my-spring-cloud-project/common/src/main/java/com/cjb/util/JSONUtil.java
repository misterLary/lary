package com.cjb.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONUtil {

    public static void main(String[] args) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        OutputStreamWriter out = null;
        try {
            // 创建远程url连接对象
            URL url = new URL("http://api.ce5553b77fb84434d8cd396e5c31b8848.cn-shenzhen.alicontainer.com/quality-service/question/getListDataForDp?currentPage=1&pageSize=300&startDate=2019-01-01");

            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization","eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50SWQiOiJhODA5NzQ3OWZhNDc0OTZhYTkzZTU5NTE4ZGQ4YWE1ZSIsImVtYWlsIjoiMTQxMjAwMDAzMzNAcXEuY29tIiwibmFtZSI6Ium7hOaZluS_nSIsInVzZXJuYW1lIjoiaHVpYmFvIiwicGhvbmVOdW1iZXIiOiIxNDEyMDAwMDMzMyIsImFjY291bnRUeXBlIjoiUEVSU09OQUwiLCJ1c2VySWQiOiI2YTUyNTY1NjEzYmM0MjE4ODdhM2M1M2MwNmZjNzAyOCIsImNvbXBhbnlJZCI6IjAzMGIxOTdiZDQ3NTQyZDZiNjg2OTE2Njc4OGI0ZDYyIiwiY29tcGFueU5hbWUiOiLmt7HlnLPluILkuqTpgJrlhaznlKjorr7mlr3lu7rorr7kuK3lv4MiLCJleHAiOjE1NjUzNjk5MTB9.whpLgJt_wRTBymp_1iQ0cDIdH_q1AA7rhWsLXEYHOSw");
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            System.out.println("code: " + connection.getResponseCode());
            System.out.println("message: " + connection.getResponseMessage());
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                JSONObject jsonObject = JSONObject.parseObject(is,null,null);
                JSONObject data = (JSONObject) jsonObject.get("data");
                JSONArray records = JSONArray.parseArray(data.get("records").toString());
                int length = records.size();
                JSONObject item;
                String str = "";
                for (int i =0 ; i < length ; i ++){
                    item = records.getJSONObject(i);
                    str = "('"+ item.getString("companyType") +"',"+
                            "'"+ item.getString("companyName") +"',"+
                            "'"+ item.getString("title") +"',"+
                            "'"+ item.getString("type") +"',"+
                            "'"+ item.getString("userName") +"',"+
                            "'"+ item.getString("userId") +"',"+
                            "'"+ item.getString("launchTime") +"',"+
                            "'"+ item.getString("appId") +"',"+
                            "'"+ item.getString("tenantId") +"',"+
                            "'"+ item.getString("contractId") +"',"+
                            "'"+ item.getString("wbsId") +"',"+
                            "'"+ item.getString("contractName") +"',"+
                            "'"+ item.getString("id") +"',"+
                            "'"+ item.getString("projectName") +"',"+
                            "'"+ item.getString("projectId") +"',"+
                            "'"+ item.getString("status") +"'),";
                    System.out.println(str);
                }
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();// 关闭远程连接
        }
    }

}
