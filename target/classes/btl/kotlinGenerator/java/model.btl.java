package ${model.pkg};

import org.beetl.sql.core.annotatoin.Table;
import java.math.*;
import java.lang.*;
import java.util.*;
import java.sql.Timestamp;
import javax.persistence.Id;

/*
* ${model.tableComment}
* gen by ruofeng945 ${date(),"yyyy-MM-dd"}
*/
@Table(name = "${model.tableName}")
public class ${model.className}{
    <%if(!isEmpty(model.attrs)){
        for(attr in model.attrs){
        if(!isEmpty(attr.columnComment)){
            println("\n    //"+attr.columnComment);
        }
        if(attr.columnKey=="PRI"){
            println("    @Id");
        }
        var columnName=kotlin.dbcolToClzAttr(attr.columnName);
        var kotlinType=kotlin.turnSqlType(attr.dataType);
        var upperFirstColumnName=kotlin.upperFirst(columnName);
%>
    private ${kotlinType} ${columnName} ;
    public ${kotlinType} get${upperFirstColumnName}(){
        return  ${columnName};
    }
    public void set${upperFirstColumnName}(${kotlinType} ${columnName}){
        this.${columnName} = ${columnName};
    }<%
}}%>}