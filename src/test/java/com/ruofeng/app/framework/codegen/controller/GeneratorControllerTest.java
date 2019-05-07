package com.ruofeng.app.framework.codegen.controller;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import com.google.common.base.CaseFormat;

public class GeneratorControllerTest {
    Logger log = LoggerFactory.getLogger("test");

    @Test
    public void bats() {
    }

    @Test
    public void pojo() {
    }

    @Test
    public void batsMakeSource() {
    }

    @Test
    public void pojo_deleteLast() {
        String target1 = "123456789";
        String target2 = "";
        log.info("target1.length():" + target1.length());
        log.info(target1.substring(0, target1.length() - 1), "12345678");
        log.info(target1.substring(1, target1.length() - 2), "12345678");
        log.info(target2.substring(0, target2.length() - 2), "");
    }

    @Test
    public void turnFormat() {
        String s = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "user_ordeR_detaiL");
        System.out.println(s);
    }
}