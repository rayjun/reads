package cn.rayjun.readhub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ray on 27/07/2017.
 */

public class TimeUtil {

    public static final String TIME_FOTMAT = "yyyy-MM-dd HH:mm:ss";

    public static String showTime(Date ctime, String format) {
        String r = "";
        if(ctime==null)return r;
        if(format==null)
            format = TIME_FOTMAT;

        long nowtimelong = System.currentTimeMillis();

        long ctimelong = ctime.getTime();
        long result = Math.abs(nowtimelong - ctimelong);

        if(result < 60000){// 一分钟内
            long seconds = result / 1000;
            if(seconds == 0){
                r = "刚刚";
            }else{
                r = seconds + "秒前";
            }
        }else if (result >= 60000 && result < 3600000){// 一小时内
            long seconds = result / 60000;
            r = seconds + "分钟前";
        }else if (result >= 3600000 && result < 86400000){// 一天内
            long seconds = result / 3600000;
            r = seconds + "小时前";
        }else if (result >= 86400000 && result < 1702967296){// 三十天内
            long seconds = result / 86400000;
            r = seconds + "天前";
        }else{// 日期格式
            format="MM-dd HH:mm";
            SimpleDateFormat df = new SimpleDateFormat(format);
            r = df.format(ctime).toString();
        }
        return r;
    }


    public static Date stringToDate(String dateString, String format) {

        SimpleDateFormat sdf = null;

        if(format == null || "".equals(format))
            sdf = new SimpleDateFormat(TIME_FOTMAT);
        else
            sdf = new SimpleDateFormat(format);

        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
