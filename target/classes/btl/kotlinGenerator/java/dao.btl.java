package ${model.pkg};

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import java.util.List;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
/*
* gen by ruofeng945 ${date(),"yyyy-MM-dd"}
*/
<%
var pkFiledName="";
var pkFiledType="";
if(!isEmpty(model.attrs)){
for(attr in model.attrs){
var columnName=kotlin.dbcolToClzAttr(attr.columnName);
var kotlinType=kotlin.turnSqlType(attr.dataType);
if(attr.columnKey=="PRI"){
pkFiledName=columnName;
pkFiledType=kotlinType;
}
}
}
%>
@SqlResource("${model.relativePkg}")
public interface ${model.className}Dao extends BaseMapper<${model.className}> {

    public void get${model.className}Page(PageQuery<${model.className}> page);

    public List<${model.className}> get${model.className}List(${model.className} model);

    public ${model.className} get${model.className}ById(@Param("pkId")${pkFiledType} pkId);
}
