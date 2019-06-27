package com.controller;

import com.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
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

}
