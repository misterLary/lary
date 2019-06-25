package com.cjb.util.webservice;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    public static void doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        OutputStreamWriter out = null;
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
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
                    String id = element.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue();
                    System.out.println(id);
                    String nick = element.getElementsByTagName("Nick").item(0).getFirstChild().getNodeValue();
                    System.out.println(nick);
                    String rtpmUrl = element.getElementsByTagName("RTMPURL").item(0).getFirstChild().getNodeValue();
                    System.out.println(rtpmUrl);
                    String httpUrl = element.getElementsByTagName("HTTPURL").item(0).getFirstChild().getNodeValue();
                    System.out.println(httpUrl);
                    System.out.println();
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

    public static void doPost(String httpUrl, String param) {

        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        OutputStreamWriter out = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //2.传入参数部分
            // 得到请求的输出流对象
            out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            out.write("byProvinceName=陕西"); //参数形式跟在地址栏的一样
            out.flush();
            out.close();

            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    System.out.println(temp);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();// 断开与远程地址url的连接
        }
    }

//    public static void main(String[] args) {
//        doPost("http://www.webxml.com.cn//WebServices/WeatherWebService.asmx/getSupportCity?byProvinceName=陕西",null);
//        doGet("http://www.webxml.com.cn//WebServices/WeatherWebService.asmx/getSupportCity?byProvinceName=陕西");
//    }
}