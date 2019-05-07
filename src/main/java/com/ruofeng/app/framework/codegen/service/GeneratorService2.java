package com.ruofeng.app.framework.codegen.service;

import com.google.common.io.Files;
import com.ruofeng.app.common.utils.StringUtils;
import com.ruofeng.app.framework.codegen.dao.GeneratorDao;
import com.ruofeng.app.framework.codegen.model.GeneratorParam;
import com.ruofeng.app.framework.codegen.model.ModelConfig;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.ResourceLoader;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.ruofeng.app.common.utils.StringUtils.templateIt;

@Service
public class GeneratorService2 {
    private Logger log = LoggerFactory.getLogger(GeneratorService2.class);
    private String kotlinPath = null;
    private String beetlsqlPath = null;
    private String webPath = null;
    private String beetlPath = System.getProperty("user.dir") + "\\src\\main\\resources\\btl\\kotlinGenerator\\";
    Map<String, File> beetlmap = null;
    @Autowired
    public GeneratorDao dao;

    public GeneratorService2() {
        this.kotlinPath = "\\src\\main\\java\\";
        this.beetlsqlPath = "\\src\\main\\resources\\sql\\";
        this.webPath = "\\src\\main\\resources\\static\\";
    }

    public void makeKotlin(GeneratorParam param) throws IOException {
        beetlmap = getBtlMap(param.getVersion());
        param.getTableList().forEach((ModelConfig it) -> {
            String pkgPath = "";
            if (param.getPublicPkg() != null) {
                pkgPath = param.getPublicPkg().replace(".", "\\");
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("param", param);
            paramMap.put("kotlinPath", kotlinPath);
            paramMap.put("pkgPath", pkgPath);
            paramMap.put("webPath", webPath);
            paramMap.put("beetlsqlPath", beetlsqlPath);
            paramMap.put("it", it);
            String htmlPath=StringUtils.subSpliString(it.getFileRelativePath(),"\\","/",-3);
            String pathTpl = "${param.projectPath}${webPath}"+htmlPath;
            paramMap.put("path", templateIt(pathTpl, paramMap));
            String[] makeTypeArray = it.getMakeType().toLowerCase().split(",");
            for (String currentType : makeTypeArray) {
                switch (currentType) {
                case "model": {
                    makeByBackFont("model", it.getClassName(), it, paramMap, param);
                    break;
                }
                case "sqlmd": {
                    File f = beetlmap.get("sql");
                    String[] extNameArray = f.getName().split("\\.");
                    String fileAbsolutePathTpl = "${param.projectPath}${beetlsqlPath}${pkgPath}${it.fileRelativePath}."
                            + extNameArray[extNameArray.length - 1];
                    String fileAbsolutePath = templateIt(fileAbsolutePathTpl, paramMap);
                    makeByRltPath(it, fileAbsolutePath, param.getVersion() + "/" + f.getName());
                    break;
                }
                case "dao": {
                    makeByBackFont("dao", it.getClassName() + "Dao", it, paramMap, param);
                    break;
                }
                case "service": {
                    makeByBackFont("service", it.getClassName() + "Service", it, paramMap, param);
                    break;
                }
                case "controller": {
                    makeByBackFont("controller", it.getClassName() + "Controller", it, paramMap, param);
                    break;
                }
                case "htmljs": {
                    makeByRltPath(it, templateIt("${path}\\list.html", paramMap),
                            param.getVersion() + "/list.btl.html");
                    makeByRltPath(it, templateIt("${path}\\list.js", paramMap), param.getVersion() + "/list.btl.js");
                    makeByRltPath(it, templateIt("${path}\\edit.html", paramMap),
                            param.getVersion() + "/edit.btl.html");
                    makeByRltPath(it, templateIt("${path}\\edit.js", paramMap), param.getVersion() + "/edit.btl.js");
                    makeByRltPath(it, templateIt("${path}\\setting.html", paramMap),
                            param.getVersion() + "/setting.btl.html");
                    makeByRltPath(it, templateIt("${path}\\setting.js", paramMap),
                            param.getVersion() + "/setting.btl.js");
                    makeByRltPath(it, templateIt("${path}\\list.html", paramMap),
                            param.getVersion() + "/list.btl.html");
                }
                }
            }
        });
    }

    public void makeByBackFont(String beetlFirstName, String fileName, ModelConfig it, Map<String, Object> paramMap,
            GeneratorParam param) {
        File f = beetlmap.get(beetlFirstName);
        String[] extNameArray = f.getName().split("\\.");
        String extendName = extNameArray[extNameArray.length - 1];
        String fileAbsolutePathTpl = "${param.projectPath}${kotlinPath}${pkgPath}${it.fileRelativePath}/" + fileName
                + "." + extendName;
        String fileAbsolutePath = templateIt(fileAbsolutePathTpl, paramMap);
        makeByRltPath(it, fileAbsolutePath, param.getVersion() + "/" + f.getName());
    }

    public void makeByRltPath(ModelConfig model, String fileAbsolutePath, String btlStr) {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("/btl/kotlinGenerator/");
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate((ResourceLoader) resourceLoader, cfg);
            Template t = gt.getTemplate(btlStr);
            t.binding("model", model);
            String str = t.render();
            this.writeStrToFile(str, fileAbsolutePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getTargetFile(String file) throws IOException {
        File modelKtFile = new File(file);
        if (!modelKtFile.getParentFile().exists()) {
            modelKtFile.getParentFile().mkdirs();
        }
        modelKtFile.createNewFile();
        return modelKtFile;
    }

    /**
     * @param content
     * @param fileStr
     * @throws IOException 写入content到fileStr对应的文件中
     */
    public void writeStrToFile(String content, String fileStr) throws IOException {
        File modelKtFile = this.getTargetFile(fileStr);
        Files.write(content.getBytes(), modelKtFile);
    }

    public Map<String, File> getBtlMap(String version) {
        Map<String, File> beetlsMap = new HashMap<String, File>();
        for (String s : new File(beetlPath + version).list()) {
            File f = new File(s);
            beetlsMap.put(f.getName().split("\\.")[0], f);
        }
        return beetlsMap;
    }
}