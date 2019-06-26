package com.util;

import feign.RequestLine;

public interface BaseDataService {

    @RequestLine("/api-redis/login/getUsers")
    Object getUserList();

}
