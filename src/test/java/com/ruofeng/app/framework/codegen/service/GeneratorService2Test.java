package com.ruofeng.app.framework.codegen.service;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorService2Test {

    @Test
    public void makeKotlin() {
        Map<String,String> kvMap=new HashMap<String,String>();
        kvMap.put("x","123456789");
        String s=kvMap.get("x");
        s="hellow";
        System.out.println(kvMap);
    }

    @Test
    public void makeByBackFont() {
    }

    @Test
    public void makeByRltPath() {
    }

    @Test
    public void getTargetFile() {
    }

    @Test
    public void writeStrToFile() {
    }

    @Test
    public void getBtlMap() {
    }
}