package com.ruofeng.app.common.utils;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlManagerUtil {
    public static final SqlManagerUtil INSTANCE;

    public ConnectionSource getDbSource(String properties) throws IOException {
        File var3 = new File(properties);
        FileInputStream ras = new FileInputStream(var3);
        return this.getDbSource((InputStream) ras);
    }

    public ConnectionSource getDbSource(InputStream ras) throws IOException {
        Properties prop = new Properties();
        prop.load(ras);
        String driver = prop.get("spring.datasource.driver-class-name").toString();
        String url = prop.get("spring.datasource.druid.url").toString();
        String userName = prop.get("spring.datasource.druid.username").toString();
        String password = prop.get("spring.datasource.druid.password").toString();
        return ConnectionSourceHelper.getSimple(driver, url, userName, password);
    }

    public SQLManager sqlmanager(String properties) throws IOException {
        return new SQLManager((DBStyle) (new MySqlStyle()), (SQLLoader) (new ClasspathLoader("/sql")),
                this.getDbSource(properties), (NameConversion) (new UnderlinedNameConversion()),
                new Interceptor[] { (Interceptor) (new DebugInterceptor()) });
    }

    public SQLManager sqlmanager() throws IOException {
        return this.sqlmanager("application.properties");
    }

    static {
        SqlManagerUtil var0 = new SqlManagerUtil();
        INSTANCE = var0;
    }
}
