package com.dmt.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import org.testng.annotations.Test;
/**
 * Created by duanjunwei on 2018/4/9.
 */
public class Main {
    @Test
    public void concurrentHashMap(){
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
        map.put("aaa","ccc");
    }
}
