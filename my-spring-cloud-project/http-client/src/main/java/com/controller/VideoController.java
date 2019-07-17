package com.controller;

import com.service.HttpService;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private HttpService redisHttpService;

    @RequestMapping("/getVideoData")
    public Object getVideoData(){
        try{
            return redisHttpService.getVideoData();
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> map = new Hashtable<String, Object>();
            map.put("error",e.getMessage());
            return map;
        }
    }

    @RequestMapping("/operateVideo")
    public Object operateVideo(Integer pid, Integer pCmd, Integer pSpeed){
        try{
            return redisHttpService.operateVideo(pid,pCmd,pSpeed);
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> map = new Hashtable<String, Object>();
            map.put("error",e.getMessage());
            return map;
        }
    }

    @RequestMapping("/httpRestTemplateTest")
    public Object httpRestTemplateTest(){
        try{
            return redisHttpService.httpRestTemplateTest();
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> map = new Hashtable<String, Object>();
            map.put("error",e.getMessage());
            return map;
        }
    }
    @RequestMapping("/eurekaRestTemplateTest")
    public Object eurekaRestTemplateTest(){
        try{
            return redisHttpService.eurekaRestTemplateTest();
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> map = new Hashtable<String, Object>();
            map.put("error",e.getMessage());
            return map;
        }
    }

    @RequestMapping("/getVideoPic")
    public void getVideoPic(String videoUrl, HttpServletResponse response){
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            URL url = new URL(videoUrl);// 创建远程url连接对象
            connection = (HttpURLConnection) url.openConnection();// 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection.setRequestMethod("GET");// 设置连接方式：get
            connection.setConnectTimeout(5000);// 设置连接主机服务器的超时时间：5000毫秒
            connection.setReadTimeout(10000);// 设置读取远程返回的数据时间：10000毫秒
            connection.connect();// 发送请求
            if (connection.getResponseCode() == 200) {// 通过connection连接，获取输入流
                is = connection.getInputStream();
                getVideoPic(is,response.getOutputStream());
            }
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

    private static void getVideoPic(InputStream inputStream, OutputStream outputStream) {
        FFmpegFrameGrabber ff = null;
        try {
            ff = new FFmpegFrameGrabber(inputStream);
            ff.start();
            int i = 0;// 截取中间帧图片(具体依实际情况而定)
            Frame frame = null;
            while (i <= 1) {
                frame = ff.grabFrame();
                if ((i > 2) && (frame.image != null)) {
                    break;
                }
                i++;
            }
            Java2DFrameConverter converter = new Java2DFrameConverter();// 截取的帧图片
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();
            // 对截图进行等比例缩放(缩略图)
            int width = 480;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            ImageIO.write(thumbnailImage, "jpg", outputStream);

            ff.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try{
                    inputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(ff!=null){
                try{
                    ff.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


}
