package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/23.
 */
public class DataUtil {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public  static String getStringTime(Date date){
        return  sdf.format(date);
    }
}
