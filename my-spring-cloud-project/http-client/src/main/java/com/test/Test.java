package com.test;

import com.iheartradio.m3u8.Encoding;
import com.iheartradio.m3u8.Format;
import com.iheartradio.m3u8.PlaylistParser;
import com.iheartradio.m3u8.data.Playlist;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {

            URL url = new URL("http://1252093142.vod2.myqcloud.com/4704461fvodcq1252093142/48c8a9475285890781000441992/playlist.m3u8");
            // 创建远程url连接对象
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(500);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(5000);
            // 发送请求
            connection.connect();
            PlaylistParser parser = new PlaylistParser(connection.getInputStream(), Format.M3U, Encoding.UTF_8);
            Playlist playlist = parser.parse();
            String urlStr = playlist.getMediaPlaylist().getTracks().get(0).getUri();
            System.out.println("urlStr : " + urlStr);
        }catch (Exception e){
            System.out.println("***************");
            e.printStackTrace();
        }finally {
            if(null != connection){
                connection.disconnect();
            }
        }

    }

}
