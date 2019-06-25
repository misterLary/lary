package com.service;

import java.util.List;
import java.util.Map;

public interface HttpService {

    List<Map<String,Object>> getVideoData();

    Object operateVideo(Integer pid, Integer pCmd, Integer pSpeed);

}
