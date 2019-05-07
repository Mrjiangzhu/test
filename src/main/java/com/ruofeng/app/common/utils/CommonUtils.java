package com.ruofeng.app.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
    ObjectMapper json = new ObjectMapper();
    Logger log = LoggerFactory.getLogger(CommonUtils.class);
}