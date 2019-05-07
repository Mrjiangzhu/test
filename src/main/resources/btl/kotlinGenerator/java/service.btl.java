package ${model.pkg};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
* gen by ruofeng945 mapper ${date(),"yyyy-MM-dd"}
*/
@Service
public class ${model.className}Service{
    Logger log = LoggerFactory.getLogger(${model.className}Service.class);
    <%var objName=kotlin.lowerFirst(model.className);%>
    @Autowired ${model.className}Dao ${objName}Dao;
    public void get${model.className}Page(PageQuery<${model.className}> page) {
        ${objName}Dao.get${model.className}Page(page);
    }
}
