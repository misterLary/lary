package com.cjb.service.impl;

import java.sql.SQLException;

import com.cjb.annotation.LogAnno;
import com.cjb.service.DictionaryService;
import org.springframework.stereotype.Service;

/**
 * 字典表的实现类
 *
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @LogAnno(operateType = "添加了一个字典项")
    @Override
    public boolean addDictionary(String message) throws Exception {
        throw new Exception("操作异常");
//        return false;
    }
}