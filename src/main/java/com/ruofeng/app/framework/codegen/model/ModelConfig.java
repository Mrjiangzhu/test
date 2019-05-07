package com.ruofeng.app.framework.codegen.model;

import java.util.List;

public class ModelConfig {
    String pkg = null;
    String fileRelativePath = null;
    String relativePkg = null;
    String tableSchema = null;
    String tableName = null;
    String className = null;
    String tableComment = null;
    List<TableRowConfig> attrs = null;
    String makeType;

    public ModelConfig() {
    }

    public ModelConfig(String pkg, String fileRelativePath, String relativePkg, String tableSchema, String tableName,
            String className, String tableComment, List<TableRowConfig> attrs, String makeType) {
        this.pkg = pkg;
        this.fileRelativePath = fileRelativePath;
        this.relativePkg = relativePkg;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.className = className;
        this.tableComment = tableComment;
        this.attrs = attrs;
        this.makeType = makeType;
    }

    public String getPkg() {
        return this.pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getFileRelativePath() {
        return this.fileRelativePath;
    }

    public void setFileRelativePath(String fileRelativePath) {
        this.fileRelativePath = fileRelativePath;
    }

    public String getRelativePkg() {
        return this.relativePkg;
    }

    public void setRelativePkg(String relativePkg) {
        this.relativePkg = relativePkg;
    }

    public String getTableSchema() {
        return this.tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableComment() {
        return this.tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<TableRowConfig> getAttrs() {
        return this.attrs;
    }

    public void setAttrs(List<TableRowConfig> attrs) {
        this.attrs = attrs;
    }

    public String getMakeType() {
        return this.makeType;
    }

    public void setMakeType(String makeType) {
        this.makeType = makeType;
    }

    public ModelConfig pkg(String pkg) {
        this.pkg = pkg;
        return this;
    }

    public ModelConfig fileRelativePath(String fileRelativePath) {
        this.fileRelativePath = fileRelativePath;
        return this;
    }

    public ModelConfig relativePkg(String relativePkg) {
        this.relativePkg = relativePkg;
        return this;
    }

    public ModelConfig tableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
        return this;
    }

    public ModelConfig tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public ModelConfig className(String className) {
        this.className = className;
        return this;
    }

    public ModelConfig tableComment(String tableComment) {
        this.tableComment = tableComment;
        return this;
    }

    public ModelConfig attrs(List<TableRowConfig> attrs) {
        this.attrs = attrs;
        return this;
    }

    public ModelConfig makeType(String makeType) {
        this.makeType = makeType;
        return this;
    }

    @Override
    public String toString() {
        return "{" + " pkg='" + getPkg() + "'" + ", fileRelativePath='" + getFileRelativePath() + "'"
                + ", relativePkg='" + getRelativePkg() + "'" + ", tableSchema='" + getTableSchema() + "'"
                + ", tableName='" + getTableName() + "'" + ", className='" + getClassName() + "'" + ", tableComment='"
                + getTableComment() + "'" + ", attrs='" + getAttrs() + "'" + ", makeType='" + getMakeType() + "'" + "}";
    }

}