package com.ruofeng.app.framework.codegen.dao;

import com.ruofeng.app.framework.codegen.model.ModelConfig;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

@SqlResource("com.ruofeng.app.framework.generator.sqlmanager")
public interface GeneratorDao extends BaseMapper<ModelConfig> {
    public void tableList(PageQuery<ModelConfig> page);
}