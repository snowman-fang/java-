package jdbc;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/12.
 */
public class Jdbc {
    public static <T> T  load(Class<T> requiredType,Object primaryKey){
        return  Dao.load(requiredType,primaryKey);
    }
    public static<T> T get(Class<T> requiredType,String sql,Object...parameters){
        return Dao.get(requiredType,sql,parameters);
    }
    public static void  update(Object object){
        Dao.update(object);
    }
    public static void insert(Object object){
        Dao.insert(object);
    }
    public static void  delete(Object object){
        Dao.delete(object);
    }
    public static<T> List<T> ListBySql(Class<T> requiredType,String sql,Object... parameters){
        return  Dao.ListBySql(requiredType,sql,parameters);
    }
    public static List<Map<String,Object>> listMapBySql(String sql,Object... parameters){
        return Dao.listMapBySql(sql,parameters);
    }
    public static void deleteBySql(String sql,Object...parameters){
        Dao.deleteBySql(sql,parameters);
    }
}
