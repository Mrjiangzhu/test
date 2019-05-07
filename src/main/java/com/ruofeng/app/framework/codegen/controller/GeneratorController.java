package com.ruofeng.app.framework.codegen.controller;

import com.ruofeng.app.framework.codegen.functions.KotlinFun;
import com.ruofeng.app.framework.codegen.model.GeneratorParam;
import com.ruofeng.app.framework.codegen.model.ModelConfig;
import com.ruofeng.app.framework.codegen.model.TableRowConfig;
import com.ruofeng.app.framework.codegen.service.GeneratorService2;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping({ "/ruofeng/app/framework/generator" })
public class GeneratorController {
    @Autowired
    public SQLManager sqlManager;
    @Autowired
    public GeneratorService2 version2Service;
    private final String sqlmdLocation = "com.ruofeng.app.framework.generator.sqlmanager";
    //private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 批量生成的处理过程
     */
    @PostMapping({ "bats" })
    public ResponseEntity<String> bats(@RequestBody GeneratorParam param) {
        this.batsMakeSource(param);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<String>("kotlin controller,service,dao,model文件，beetlsql生成成功！", status);
    }

    /**
     * 单个表的生成过程
     */
    @PostMapping({ "pojo" })
    public ResponseEntity<GeneratorParam> pojo(@RequestBody GeneratorParam param) {
        HttpStatus status = HttpStatus.OK;
        this.batsMakeSource(param);
        return new ResponseEntity<GeneratorParam>(param, status);
    }

    /**
     * 生成过程的公共函数
     */
    public final void batsMakeSource(GeneratorParam param) {

        for (ModelConfig model : param.getTableList()) {
            List<TableRowConfig> columnList = sqlManager.select(sqlmdLocation + ".columnList", TableRowConfig.class,
                    model);
            model.setAttrs(columnList);
            if (model.getMakeType() == null || model.getMakeType().isEmpty()) {
                model.setMakeType("model,dao,service,controller,sqlmd,htmljs,menu");
            }
            if (model.getClassName() == null || model.getClassName().isEmpty()) {
                model.setClassName(KotlinFun.underToUpperCamel(model.getTableName()));
            }
            if (model.getPkg() == null || model.getPkg().isEmpty()) {
                String pkgTempString = param.getParentPkg() + "." + KotlinFun.lowerFirst(model.getClassName());
                model.setPkg(pkgTempString);
            }
            if (param.getPublicPkg() != null) {
                model.setRelativePkg(model.getPkg().replace(param.getPublicPkg(), ""));
            } else {
                model.setRelativePkg(model.getPkg());
            }
            model.setFileRelativePath(model.getRelativePkg().replace(".", "\\"));
        }
        if (param.getVersion() != null) {
            try {
                version2Service.makeKotlin(param);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
