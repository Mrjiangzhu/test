columnList
===
SELECT
	*
FROM
	information_schema.COLUMNS t
WHERE
	1 = 1
AND t.TABLE_SCHEMA = #tableSchema#
@if(!isEmpty(tableName)){
AND table_name = #tableName#
@}



tableList
===
SELECT
	#page("t.table_name,t.table_comment,t.table_schema")#
FROM
	information_schema.TABLES t
WHERE
1=1
@if(!kotlin.isNullOrEmpty(tableSchema)){
    AND TABLE_SCHEMA = #tableSchema#
@}
@if(!kotlin.isNullOrEmpty(tableComment)){
    AND table_Comment like concat('%',#tableComment#,'%')
@}
@if(!kotlin.isNullOrEmpty(tableName)){
    AND table_name like concat('%',#tableName#,'%')
@}
ORDER BY table_rows DESC



checkMenuReady
===
select * from menu t where t.href=#href#