package com.cjb.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;

/**
 * 解析XML
 */
public class XMLUtil {

    public static final java.net.URL URL = XMLUtil.class.getResource("/data.xml");

    public static void main(String[] args) {
        try{
            DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = bdf.newDocumentBuilder();
            Document document = db.parse(new File(URL.toURI()));
            NodeList list = document.getElementsByTagName("TDev");
            System.out.println("脚本之家测试结果：");
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
