cols
===
* 所有列：${model.tableComment}
<%
for(attr in model.attrs){
  if(attrLP.index==attrLP.size){
    print("t."+attr.columnName);
  } else {
    print("t."+attr.columnName+" ,");
  }
}%>




condition
===
WHERE 1=1
<%
for(attr in model.attrs){
    var columnName=kotlin.dbcolToClzAttr(attr.columnName);
    var kotlinType=kotlin.turnSqlType(attr.dataType);
    switch(kotlinType){
        case "String":
%>
@if(kotlin.isNullOrEmpty(${columnName})==false){
  AND t.${attr.columnName} like concat('%',#${columnName}#,'%')
@}
<%
        break;
        default:
%>
@if(isNotEmpty(${columnName})){
  AND t.${attr.columnName} = #${columnName}#
@}
<%
    }/* end of switch(kotlinType)*/
}/* end of for(attr in model.attrs)*/
%>



get${model.className}Page
===
select #page("*")# from `${model.tableName}` t #use("condition")#


get${model.className}List
===
select #use("cols")# from `${model.tableName}` t #use("condition")#

get${model.className}
===
select #use("cols")# from `${model.tableName}` t

