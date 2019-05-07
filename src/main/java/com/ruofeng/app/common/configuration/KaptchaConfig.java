package com.ruofeng.app.common.configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {
   @Bean
   public DefaultKaptcha producer() {
      Properties properties = new Properties();
      String var3 = "kaptcha.border";
      String var4 = "no";
      properties.put(var3, var4);
      var3 = "kaptcha.textproducer.font.color";
      var4 = "black";
      properties.put(var3, var4);
      var3 = "kaptcha.textproducer.char.space";
      var4 = "5";
      properties.put(var3, var4);
      Config config = new Config(properties);
      DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
      defaultKaptcha.setConfig(config);
      return defaultKaptcha;
   }
}
