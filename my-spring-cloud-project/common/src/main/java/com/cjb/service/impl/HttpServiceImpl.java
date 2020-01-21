package com.cjb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cjb.service.HttpService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class HttpServiceImpl implements HttpService {

    @Override
    public List<Map<String, Object>> getVideoData() {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            // 创建远程url连接对象
            URL url = new URL("http://10.0.1.76/ptz.asmx/GetDeviceList");
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = bdf.newDocumentBuilder();
                Document document = db.parse(is);
                NodeList list = document.getElementsByTagName("TDev");
                for (int i = 0; i < list.getLength(); i++) {
                    Element element = (Element)list.item(i);
                    Map<String, Object> map = new Hashtable<String, Object>();
                    map.put("id",element.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue());
                    map.put("nick",element.getElementsByTagName("Nick").item(0).getFirstChild().getNodeValue());
                    map.put("rtpmUrl",element.getElementsByTagName("RTMPURL").item(0).getFirstChild().getNodeValue());
                    map.put("httpUrl",element.getElementsByTagName("HTTPURL").item(0).getFirstChild().getNodeValue());
                    result.add(map);
                }
                return result;
            }
            Map<String, Object> map = new Hashtable<String, Object>();
            map.put("error","接口异常");
            result.add(map);
            return result;
        }catch (MalformedURLException e) {
            e.printStackTrace();
            Map<String, Object> map = new Hashtable<String, Object>();
            map.put("error",e.getMessage());
            result.add(map);
            return result;
        }catch (IOException e) {
            e.printStackTrace();
            Map<String, Object> map = new Hashtable<String, Object>();
            map.put("error",e.getMessage());
            result.add(map);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> map = new Hashtable<String, Object>();
            map.put("error",e.getMessage());
            result.add(map);
            return result;
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

    @Override
    public Object operateVideo(Integer pId, Integer pCmd, Integer pSpeed) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            // 创建远程url连接对象
            URL url = new URL("http://10.0.1.76/ptz.asmx/PTZCmd?PID="+pId+"&pCmd="+pCmd+"&pSpeed="+pSpeed);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                result.put("success",connection.getResponseMessage());
                return result;
            }
            result.put("error","接口异常");
            return result;
        }catch (MalformedURLException e) {
            e.printStackTrace();
            result.put("error",e.getMessage());
            return result;
        }catch (IOException e) {
            e.printStackTrace();
            result.put("error",e.getMessage());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("error",e.getMessage());
            return result;
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

    public static void main(String[] args) {
        String uri="http://xm.test.js.tqmdp.net/api/sggProxy/camera/previewUrl?id=392ec901be484889abc63b4ad90ca076&token=4433f314-c210-4301-8db9-a2e3d5e51e15";
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate=new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String strbody=restTemplate.exchange(uri, HttpMethod.POST, entity,String.class).getBody();
        JSONObject jsonObject = (JSONObject)JSONObject.parse(strbody);
        System.out.println(jsonObject.get("data"));
    }

}
