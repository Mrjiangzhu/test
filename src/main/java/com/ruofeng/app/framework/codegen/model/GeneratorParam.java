package com.ruofeng.app.framework.codegen.model;

import java.util.List;

public class GeneratorParam {

    private String parentPkg;

    private String projectName;

    private String publicPkg;

    private String version;

    private String projectPath;

    private List<ModelConfig> tableList;

    public GeneratorParam() {
    }

    public String getParentPkg() {
        return this.parentPkg;
    }

    public void setParentPkg(String var1) {
        this.parentPkg = var1;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String var1) {
        this.projectName = var1;
    }

    public String getPublicPkg() {
        return this.publicPkg;
    }

    public void setPublicPkg(String var1) {
        this.publicPkg = var1;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion( String version) {
        this.version = version;
    }

    public String getProjectPath() {
        return this.projectPath;
    }

    public void setProjectPath(String var1) {
        this.projectPath = var1;
    }

    public List<ModelConfig> getTableList() {
        return this.tableList;
    }

    public void setTableList(List<ModelConfig> var1) {
        this.tableList = var1;
    }

    public GeneratorParam(String parentPkg, String projectName, String publicPkg, String version,
            String projectPath, List<ModelConfig> tableList) {
        super();
        this.parentPkg = parentPkg;
        this.projectName = projectName;
        this.publicPkg = publicPkg;
        this.version = version;
        this.projectPath = projectPath;
        this.tableList = tableList;
    }
}
