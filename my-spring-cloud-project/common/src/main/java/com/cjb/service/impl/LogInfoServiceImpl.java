package com.cjb.service.impl;

import com.cjb.model.LogInfo;
import com.cjb.service.LogInfoService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Override
    public boolean addLog(LogInfo log) throws SQLException {
        System.out.println(log.toString());
        return false;
    }
}
