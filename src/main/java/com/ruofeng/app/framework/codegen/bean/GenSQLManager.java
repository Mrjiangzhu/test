package com.ruofeng.app.framework.codegen.bean;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.ext.gen.MDCodeGen;

import java.io.IOException;
import java.io.Writer;
public class GenSQLManager {
   private final SQLManager sqlManager;

   public void genSQLTemplate(String table,  Writer w,  String alias) throws IOException {
      MDCodeGen mdCodeGen = new MDCodeGen();
      TableDesc desc = this.sqlManager.getMetaDataManager().getTable(table);
      mdCodeGen.genCode(this.sqlManager.getBeetl(), desc, this.sqlManager.getNc(), alias, w);
   }

   public GenSQLManager(SQLManager sqlManager) {
      super();
      this.sqlManager = sqlManager;
   }
}
