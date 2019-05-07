package com.ruofeng.app.common.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

public class BeetlSqlConfiguration {
   @Bean(name = { "datasource" }, initMethod = "init", destroyMethod = "close")
   @ConfigurationProperties(prefix = "spring.datasource.druid")
   public DataSource dataSource() {
      return (DataSource) (new DruidDataSource());
   }
   @Bean(name = { "txManager" })
   public DataSourceTransactionManager getDataSourceTransactionManager(
         @Qualifier("datasource") DataSource datasource) {
      DataSourceTransactionManager dsm = new DataSourceTransactionManager();
      dsm.setDataSource(datasource);
      return dsm;
   }
}
