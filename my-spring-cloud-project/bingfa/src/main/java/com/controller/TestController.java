package com.controller;

import com.cjb.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/addDictionary")
    public void add() throws Exception{
        dictionaryService.addDictionary("我来了");
    }

}
