package com.cjb.util.webservice;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;

public class SoapRequestUtil {

    public static void main(String[] args) throws Exception {
        String wsdl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
        int timeout = 10000;
        StringBuffer sb = new StringBuffer("");
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getSupportDataSet xmlns=\"http://WebXml.com.cn/\" />\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>");

        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(wsdl);
        client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);// 设置连接超时
        client.getHttpConnectionManager().getParams().setSoTimeout(timeout);// 设置读取时间超时
        RequestEntity requestEntity = new StringRequestEntity(sb.toString(), "text/xml", "UTF-8");// 然后把Soap请求数据添加到PostMethod中
        postMethod.setRequestHeader("SOAPAction","http://WebXml.com.cn/getSupportDataSet");//设置请求头部，否则可能会报 “no SOAPAction header” 的错误
        postMethod.setRequestEntity(requestEntity);// 设置请求体
        int status = client.executeMethod(postMethod);
        InputStream is = postMethod.getResponseBodyAsStream();// 获取响应体输入流
        String result = IOUtils.toString(is);// 获取请求结果字符串
        URL url = new URL(wsdl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setConnectTimeout(timeout);
        conn.setReadTimeout(timeout);
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.write(sb.toString().getBytes("utf-8"));
        dos.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String line = null;
        StringBuffer strBuf = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            strBuf.append(line);
        }
        dos.close();
        reader.close();
        System.out.println(strBuf.toString());
    }

}