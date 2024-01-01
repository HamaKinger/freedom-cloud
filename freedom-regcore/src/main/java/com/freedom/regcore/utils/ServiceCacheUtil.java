package com.freedom.regcore.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.freedom.regcore.model.Instance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author freedom
 * @Create on : 2023/12/30 14:30
 **/
public class ServiceCacheUtil {
    private ServiceCacheUtil(){}
    public static final Map<String,JSONArray> SERVICE = new HashMap<>();
}
