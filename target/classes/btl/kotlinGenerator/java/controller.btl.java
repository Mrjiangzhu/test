package ${model.pkg};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

        /*
         * gen by ruofeng945 controller ${date(),"yyyy-MM-dd"}
         */
        <%var urlPath=kotlin.packageToUrl(model.pkg);
        debug(model.pkg);
        debug(urlPath);
        var objName=kotlin.lowerFirst(model.className);
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
        }%>

@RestController
@RequestMapping("${urlPath}")
public class ${model.className}Controller{
    Logger log=LoggerFactory.getLogger(${model.className}Controller.class);
    @Autowired
    ${model.className}Service service;
    @Autowired
    ObjectMapper jsonObjectMapper;
    /**
    *
    */
    @GetMapping()
    public ResponseEntity<PageQuery<${model.className}>> get${model.className}Page(
            ${model.className} ${objName},PageQuery<${model.className}> page)
    {
         page.setParas(${objName});
         service.get${model.className}Page(page);
         HttpStatus status =null;
          if (page.getList().size() > 0) 
            status=HttpStatus.OK; 
          else 
            status=HttpStatus.NOT_FOUND;
         return new ResponseEntity<PageQuery<${model.className}>>(page, status);
    }

    /**
    *
    */
    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody ${model.className} ${objName})
    {
        ResponseEntity<String> result = null;
        try {
            service.${objName}Dao.updateById(${objName});
            result=new ResponseEntity<String>("${objName}更新成功", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            result=new ResponseEntity<String>("${objName}更新失败", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return result;
        }
    }

    /**
    *
    */
    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody ${model.className} ${objName})
    {
        ResponseEntity<String> result= null;
        try {
            service.${objName}Dao.insertReturnKey(${objName});
            log.info("执行插入之后额实体对象数据是:\n"+jsonObjectMapper.writeValueAsString(${objName}));
            result=new ResponseEntity<String>("${objName}保存成功", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            result=new ResponseEntity<String>("${objName}保存失败", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return result;
        }
    }

    /**
    *
    */
    @DeleteMapping()
    public ResponseEntity<String> delete(${pkFiledType} ${pkFiledName})
    {
        ResponseEntity<String> result = null;
        try {
            Integer deletedNum = service.${objName}Dao.deleteById(${pkFiledName});
            result=new ResponseEntity<String>("成功删除"+deletedNum+"条", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            result=new ResponseEntity<String>("删除失败", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return result;
        }
    }
}