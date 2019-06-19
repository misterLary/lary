package com.cjb.util.webservice;

import com.cjb.util.webservice.client.WeatherWebServiceStub;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Demo {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        try {
            WeatherWebServiceStub webServiceStub = new WeatherWebServiceStub();
            WeatherWebServiceStub.GetSupportCity supportCity = new WeatherWebServiceStub.GetSupportCity();
            String str = "陕西";
            str = new String(str.getBytes("gbk"),"utf-8");
            supportCity.setByProvinceName(str);//直接传递乱码
            WeatherWebServiceStub.GetSupportCityResponse response = webServiceStub.getSupportCity(supportCity);
            WeatherWebServiceStub.ArrayOfString arrayOfString = response.getGetSupportCityResult();

            String[] strArray = arrayOfString.getString();
            if(strArray != null && strArray.length > 0){
                int length = strArray.length;
                for (int i = 0; i < length; i ++){
                    System.out.println(strArray[i]);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void demo(){

        HttpClient httpClient=new HttpClient();
        PostMethod postMethod=new PostMethod();
        postMethod.setPath("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl"); //路径和wsdl名

        String soap = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getSupportCity xmlns=\"http://WebXml.com.cn/\">\n" +
                "      <byProvinceName>string</byProvinceName>\n" +
                "    </getSupportCity>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";

        try {
            byte[] b=soap.getBytes("utf-8");

            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length,
                    "application/soap+xml; charset=utf-8");
            postMethod.setRequestEntity(re);
            int statusCode = httpClient.executeMethod(postMethod);

            String soapResponseData = postMethod.getResponseBodyAsString();
            System.out.println("soapResponseData: " + soapResponseData);
            postMethod.releaseConnection();
//            System.out.println(soapResponseData.split("<response xsi:type=\"xsd:string\">")[1].split("</response>")[0]);
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
