package com.ruofeng.app.framework.codegen.model;

public class TableRowConfig {
    private String numericPrecision = null;
    private String columnDefault = null;
    private String dataType = null;
    private String columnComment = null;
    private Integer ordinalPosition = 0;
    private String tableSchema = null;
    private String datetimePrecision = null;
    private String tableName = null;
    private Integer characterOctetLength;
    private String columnType = null;
    private String isNullable = null;
    private String extra = null;
    private Integer characterMaximumLength = 0;
    private String columnKey = null;
    private String numericScale = null;
    private String columnName = null;

    public TableRowConfig() {
    }

    public TableRowConfig(String numericPrecision, String columnDefault, String dataType, String columnComment,
                          Integer ordinalPosition, String tableSchema, String datetimePrecision, String tableName,
                          Integer characterOctetLength, String columnType, String isNullable, String extra,
                          Integer characterMaximumLength, String columnKey, String numericScale, String columnName) {
        this.numericPrecision = numericPrecision;
        this.columnDefault = columnDefault;
        this.dataType = dataType;
        this.columnComment = columnComment;
        this.ordinalPosition = ordinalPosition;
        this.tableSchema = tableSchema;
        this.datetimePrecision = datetimePrecision;
        this.tableName = tableName;
        this.characterOctetLength = characterOctetLength;
        this.columnType = columnType;
        this.isNullable = isNullable;
        this.extra = extra;
        this.characterMaximumLength = characterMaximumLength;
        this.columnKey = columnKey;
        this.numericScale = numericScale;
        this.columnName = columnName;
    }

    public String getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(String numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(String datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(Integer characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Integer getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(Integer characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(String numericScale) {
        this.numericScale = numericScale;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}