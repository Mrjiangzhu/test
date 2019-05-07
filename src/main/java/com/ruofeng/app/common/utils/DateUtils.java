package com.ruofeng.app.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
   Logger log = LoggerFactory.getLogger("DateUtils");

   public static String parseTimestamp(Date date, String format) {
      return new SimpleDateFormat(format).format(date);
   }

   public final Date parseDate(String dateStr, String format) {
      Date date = (Date) null;

      try {
         SimpleDateFormat dateFormat = new SimpleDateFormat(format);
         date = dateFormat.parse(dateStr);
      } catch (Exception var5) {
         log.error(var5.getMessage());
      }

      return date;
   }

}
