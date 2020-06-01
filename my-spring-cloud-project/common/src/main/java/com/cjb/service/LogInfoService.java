package com.cjb.service;

import com.cjb.model.LogInfo;

import java.sql.SQLException;

public interface LogInfoService {

    /**
     * 增加日志
     * @param log
     * @return
     * @throws SQLException
     */
    public boolean addLog(LogInfo log) throws SQLException;

}
