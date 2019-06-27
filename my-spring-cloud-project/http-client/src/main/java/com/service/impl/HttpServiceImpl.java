package com.service.impl;

import com.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service(value = "redisHttpService")
public class HttpServiceImpl implements HttpService {

    @Autowired
    private RestTemplate httpRestTemplate;

    @Autowired
    private RestTemplate eurekaRestTemplate;

    @Override
    public List<Map<String, Object>> getVideoData() {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            // 创建远程url连接对象
            URL url = new URL("http://192.168.92.150/ptz.asmx/GetDeviceList");
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
                    map.put("rtspUrl",element.getElementsByTagName("RTSPURL").item(0).getFirstChild().getNodeValue());
                    result.add(map);
                }
                return result;
            }
            Map<String, Object> map = new Hashtable<String, Object>();
            map.put("error",connection.getResponseMessage());
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
            URL url = new URL("http://192.168.92.150/ptz.asmx/PTZCmd?PID="+pId+"&pCmd="+pCmd+"&pSpeed="+pSpeed);
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
            result.put("error",connection.getResponseMessage());
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

    @Override
    public Object httpRestTemplateTest() {
        return this.httpRestTemplate.getForObject("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getSupportCity?byProvinceName=", String.class);
    }

    @Override
    public Object eurekaRestTemplateTest() {
        return this.eurekaRestTemplate.getForObject("http://service-baseData/user/getUsers", Object.class);
    }
}
