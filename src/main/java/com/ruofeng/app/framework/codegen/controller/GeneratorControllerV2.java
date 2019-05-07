package com.ruofeng.app.framework.codegen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruofeng.app.framework.codegen.model.ModelConfig;
import com.ruofeng.app.framework.codegen.service.GeneratorService2;
import org.beetl.sql.core.engine.PageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ruofeng/app/framework/generator")
public class GeneratorControllerV2 {
    String sqlmdLocation = "com.ruofeng.app.framework.generator.sqlmanager";
    @Autowired
    ObjectMapper jsonObjectMapper;
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    GeneratorService2 version2Service;

    @GetMapping("/tableList")
    public ResponseEntity<PageQuery<ModelConfig>> tableList(ModelConfig modelConfig, PageQuery<ModelConfig> page) {
        page.setParas(modelConfig);
        version2Service.dao.tableList(page);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<PageQuery<ModelConfig>>(page, status);
    }
}