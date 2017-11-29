package jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
public class Dao {
	private static Logger log =Logger.getLogger("") ;
	private static  ConnectionFactory factory=new ConnectionFactory();
	private static Connection conn=factory.getConnection();
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
	
	
	private static String  getCloum(String key){
		StringBuffer str=new StringBuffer();
		for(int j=0;j<key.length();j++){
			if(Character.isUpperCase(key.charAt(j))){
				str.append("_");
				str.append((key.charAt(j)+"").toLowerCase());
			}
			else{
				str.append(key.charAt(j));
			}
		}
		String keyName=str+"";
		return keyName;
	}
	
	private static String getColunName(String name){
		StringBuffer str =new StringBuffer();
		for(int j=0;j<name.length();j++){
			if(Character.isUpperCase(name.charAt(j))){
				str.append("_");
				str.append(name.charAt(j)+"");
			}
			else{
				str.append((name.charAt(j)+"").toUpperCase());
			}
		}
		return str.toString();
	}
	
	private static String getTable(String className){
		int i=className.lastIndexOf(".")+1;
		String name=className.substring(i);
		StringBuffer str=new StringBuffer();
		for(int j=0;j<name.length();j++){
			if(j==0){
				str.append((name.charAt(j)+"").toLowerCase());
			}
			else{
				if(Character.isUpperCase(name.charAt(j))){
					str.append("_");
					str.append((name.charAt(j)+"").toLowerCase());
				}
				else{
					str.append(name.charAt(j));
				}
			}
		}
		String str0=str+"";
		return str0;
	}
	
	private static String getSymName(String cloumnName){
		StringBuffer str =new StringBuffer();
		for(int j=0;j<cloumnName.length();j++){
			if((cloumnName.charAt(j)+"").equals("_")){
				str.append((cloumnName.charAt(j+1)+"").toUpperCase());
				j++;
			}
			else{
				str.append((cloumnName.charAt(j)+"").toLowerCase());
			}
		}
		
		return str.toString();
	}

	public static <T> T  load(Class<T> requiredType,Object primaryKey){
		String table=Dao.getTable(requiredType+"");
		 Field[] fields =requiredType.getDeclaredFields();
		  Method[] methods = requiredType.getMethods();
	      ArrayList<Field> col = new ArrayList<Field>();
	      ArrayList<Object> values = new ArrayList<Object>();
	      Map<String,Method> methodMap = new HashMap<String,Method>();
	      T o=null;
	      try {
	           
	        for(int i=0;i<fields.length;i++){
	                Field field = fields[i];
	                field.setAccessible(true);
	                col.add(field);
	        }
	       
	        for(int i=0;i<methods.length;i++){
	            Method method = methods[i];
	            methodMap.put(method.getName(),method);
	        }
	        } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	                return null;
	        }

		 String sql1="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='"+table+ 
				"' AND COLUMN_KEY='PRI'";
		   try {
				PreparedStatement pstm =conn.prepareStatement(sql1);
				ResultSet rs=pstm.executeQuery();
				String cloum="";
				while(rs.next()){
					cloum=rs.getString("COLUMN_NAME");
				}
				String sql="select*from "+table+" where "+cloum+"="+primaryKey;/*+
						" or "+cloum+"='"+primaryKey+"'";*/
				PreparedStatement pst =conn.prepareStatement(sql);
				ResultSet res=pst.executeQuery();
				while(res.next()){
					 o = requiredType.newInstance();
					  	Iterator<Field> col_iter = col.iterator();
		                while(col_iter.hasNext()){
		                    Field f = col_iter.next();
		                    String colname = f.getName();

		                    String sub = colname.substring(0,1);
		                    colname = colname.substring(1);
		                    sub = sub.toUpperCase();
		                    colname = sub+colname;
		                    Method setter = methodMap.get("set"+colname);
							String COLUMN_NAME=Dao.getColunName(f.getName());
		                    setter.invoke(o, res.getObject(COLUMN_NAME));		                }
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        factory.close(conn);
		return o;
	}
	
	
	public static<T> T get(Class<T> requiredType,String sql,Object...parameters){
		  String table=Dao.getTable(requiredType+"");
		  Field[] fields =requiredType.getDeclaredFields();
		  Method[] methods = requiredType.getMethods();
	      ArrayList<Field> col = new ArrayList<Field>();
	      ArrayList<Object> values = new ArrayList<Object>();
	      Map<String,Method> methodMap = new HashMap<String,Method>();
	      T o=null;
	      try {
	           
	        for(int i=0;i<fields.length;i++){
	                Field field = fields[i];
	                field.setAccessible(true);
	                col.add(field);
	        }
	       
	      for(int i=0;i<methods.length;i++){
	            Method method = methods[i];
	            methodMap.put(method.getName(),method);
	        }
	        } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	                return null;
	        }
	     
	      PreparedStatement pst;
	     try {
			pst = conn.prepareStatement(sql);
			for(int i=0;i<parameters.length;i++){
				pst.setObject(i+1,parameters[i]);
			}
			ResultSet res=pst.executeQuery();
			while(res.next()){
				 o = requiredType.newInstance();
				  	Iterator<Field> col_iter = col.iterator();
	                while(col_iter.hasNext()){
	                    Field f = col_iter.next();
	                    String colname = f.getName();
	                    
	                    String sub = colname.substring(0,1);
	                    colname = colname.substring(1);
	                    sub = sub.toUpperCase();
	                    colname = sub+colname;
	                    Method setter = methodMap.get("set"+colname);
	                   
	                    String COLUMN_NAME=Dao.getColunName(f.getName());
	                    setter.invoke(o, res.getObject(COLUMN_NAME));
					}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory.close(conn);
		return o;
	}
	
	

	public static void  update(Object object){
		Class<? extends Object> Class = object.getClass();
		String table=Dao.getTable(Class+"");
		 String sql1="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='"+table+ 
					"' AND COLUMN_KEY='PRI'";
		
		 PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql1);
			ResultSet rs=pstm.executeQuery();
			String cloum="";
			while(rs.next()){
				cloum=rs.getString("COLUMN_NAME");
			}
			String pkName=Dao.getSymName(cloum);
			Field[] fields =Class.getDeclaredFields();
			Object value=null;
			for(int i=0;i<fields.length;i++){
				Field field = fields[i];
				if(field.getName().equals(pkName)){
					field.setAccessible(true);
					String methodName = field.getName().substring(0, 1).toUpperCase()+ field.getName().substring(1); 
						Method method = Class.getMethod("get" + methodName);
						String classType = field.getType().toString();  
			            int lastIndex = classType.lastIndexOf(".");  
			            classType = classType.substring(lastIndex + 1);
			            if(classType.equals("String")){
			            	 value = "'"+method.invoke(object)+"'"; 
			            }
			            else{
			            	 value = method.invoke(object); 
			            }
			            
						 
				}
               
            }
			ArrayList<String> colums = new ArrayList<String>();
			ArrayList<String> types = new ArrayList<String>();
	        ArrayList<Object> values = new ArrayList<Object>();
	        for(int i=0;i<fields.length;i++){
	            Field field = fields[i];
	            field.setAccessible(true);
	            Object val = field.get(object);
				String classType = field.getType().toString();  
	            int lastIndex = classType.lastIndexOf(".");  
	            classType = classType.substring(lastIndex + 1); 
	            types.add(classType);
	            values.add(val);
	            colums.add(Dao.getCloum(field.getName()));
	        }
	       
	        StringBuffer sql=new StringBuffer();
	        sql.append("update "+table+" set ");
	        for(int j=0;j<colums.size();j++){
	        	Object o=values.get(j);
	        	String c="'"+o+"'";
	          if(types.get(j).equals("String")){
	        	  	if(j<colums.size()-1){
		        		sql.append(colums.get(j)+"="+c+",");
		        	}
		        	else{
		        		sql.append(colums.get(j)+"="+c+" where "+cloum+"="+value);
		        	}
	          }
	          else{
	        	  if(j<colums.size()-1){
		        		sql.append(colums.get(j)+"="+o+",");
		        	}
		        	else{
		        		sql.append(colums.get(j)+"="+o+" where "+cloum+"="+value);
		        	}
	          }
	        	
	        }
	     conn.setAutoCommit(false);  
   	     PreparedStatement pt =conn.prepareStatement(sql.toString());
   	     pt.executeUpdate();
   	     conn.commit();
   	     factory.close(conn);
   	     log.info("修改"+table+"一条数据成功!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	
	public static void insert(Object object){
		Class<? extends Object> Class = object.getClass();
		Field[] fields = Class.getDeclaredFields();
		ArrayList<String> colums = new ArrayList<String>();
		ArrayList<String> types = new ArrayList<String>();
        ArrayList<Object> values = new ArrayList<Object>();
    	try {
        for(int i=0;i<fields.length;i++){
            Field field = fields[i];
            field.setAccessible(true);
            Object val = field.get(object);
			String classType = field.getType().toString();  
            int lastIndex = classType.lastIndexOf(".");  
            classType = classType.substring(lastIndex + 1); 
            types.add(classType);
            values.add(val);
            colums.add(Dao.getCloum(field.getName()));
        }
        String table=Dao.getTable(Class+"");
      
        StringBuffer sql=new StringBuffer();
        sql.append("insert into "+table+"(");
        for(int j=0;j<colums.size();j++){
        	if(j<colums.size()-1){
        		sql.append(colums.get(j)+",");
        	}
        	else{
        		sql.append(colums.get(j)+")values(");
        	}
        }
        
        for(int j=0;j<colums.size();j++){
        	Object o=values.get(j);
        	String c="'"+o+"'";
        	if(types.get(j).equals("String")){
        		if(j<colums.size()-1){
            		sql.append(c+",");
            	}
            	else{
            		sql.append(c+")");
            	}
        	}else if(types.get(j).equals("Date")){
        		String m=null;
        		if(null!=o){
        			m="'"+sdf.format(o)+"'";
        		}
        		
        	if(j<colums.size()-1){
            		sql.append(m+",");
            	}
            	else{
            		sql.append(m+")");
            	}
        	}else{
        		if(j<colums.size()-1){
            		sql.append(o+",");
            	}
            	else{
            		sql.append(o+")");
            	}
        	}
        	
        }
        
        PreparedStatement pstm =conn.prepareStatement(sql.toString());
        pstm.executeUpdate();
        factory.close(conn);
        log.info("插入"+table+"一条数据成功!");
    	} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
	

	
	public static void  delete(Object object){
		Class<? extends Object> Class = object.getClass();
		
		Field[] fields = Class.getDeclaredFields();
		ArrayList<String> colums = new ArrayList<String>();
		ArrayList<String> types = new ArrayList<String>();
        ArrayList<Object> values = new ArrayList<Object>();
        ConnectionFactory factory=new ConnectionFactory();
		Connection conn=factory.getConnection();
        try {
        	for(int i=0;i<fields.length;i++){
        		Field field = fields[i];
        		field.setAccessible(true);
        		Object val = field.get(object);
        		String classType = field.getType().toString();  
        		int lastIndex = classType.lastIndexOf(".");  
        		classType = classType.substring(lastIndex + 1); 
        		types.add(classType);
        		values.add(val);
        		colums.add(Dao.getCloum(field.getName()));
        		}
        		String table=Dao.getTable(Class+"");
        		StringBuffer sql=new StringBuffer();
        		sql.append("delete from "+table+" where 1=1 and ");
        		for(int i=0;i<colums.size();i++){
        			Object o=values.get(i);
                	String c="'"+o+"'";
                 if(types.get(i).equals("String")){
        			if(i<colums.size()-1){
        				sql.append(colums.get(i)+"="+c+" and ");
        			}
        			else{
        				sql.append(colums.get(i)+"="+c);
        			}
                  }else if(types.get(i).equals("Date")){
              		String m=null;
            		if(null!=o){
            			m="'"+sdf1.format(o)+"'";
            		}
            		if(i<colums.size()-1){
            			if(null!=null){
                		sql.append(colums.get(i)+"="+m+" and ");
            			}else{
            				sql.append(colums.get(i)+" is "+m+" and ");
            			}
                	}
                	else{
                		if(null!=null){
                    		sql.append(colums.get(i)+"="+m);
                			}else{
                				sql.append(colums.get(i)+" is "+m);
                			}
                	}
            	}else{
                	 if(i<colums.size()-1){
         				sql.append(colums.get(i)+"="+o+" and ");
         			}
         			else{
         				sql.append(colums.get(i)+"="+o);
         			}
                 }
        		}
        		
        		 conn.setAutoCommit(false);  
        	     PreparedStatement pstm =conn.prepareStatement(sql.toString());
        	     pstm.execute();
        	     conn.commit();
        	     factory.close(conn);
        	     log.info("删除"+table+"一条数据成功!");
        	    
        } catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	
	public static<T> List<T> ListBySql(Class<T> requiredType,String sql,Object... parameters){
		  String table=Dao.getTable(requiredType+"");
		  Field[] fields =requiredType.getDeclaredFields();
		  Method[] methods = requiredType.getMethods();
	      ArrayList<Field> col = new ArrayList<Field>();
	      ArrayList<Object> values = new ArrayList<Object>();
	      Map<String,Method> methodMap = new HashMap<String,Method>();
	      T o=null;
	      try {
	           
	        for(int i=0;i<fields.length;i++){
	                Field field = fields[i];
	                field.setAccessible(true);
	                col.add(field);
	        }
	       
	        for(int i=0;i<methods.length;i++){
	            Method method = methods[i];
	            methodMap.put(method.getName(),method);
	        }
	        } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	                return null;
	        }
	   
	      PreparedStatement pst;
	      List<T> list=new ArrayList<T>();
		try {
			pst = conn.prepareStatement(sql);
			for(int i=0;i<parameters.length;i++){
				pst.setObject(i+1,parameters[i]);
			}
			ResultSet res=pst.executeQuery();
			while(res.next()){
				 o = requiredType.newInstance();
				  	Iterator<Field> col_iter = col.iterator();
	                while(col_iter.hasNext()){
	                    Field f = col_iter.next();
	                    String colname = f.getName();
	                    String sub = colname.substring(0,1);
	                    colname = colname.substring(1);
	                    sub = sub.toUpperCase();
	                    colname = sub+colname;
	                    Method setter = methodMap.get("set"+colname);
	                   
	                    String COLUMN_NAME=Dao.getColunName(f.getName());
	                    setter.invoke(o, res.getObject(COLUMN_NAME));	
	              }
	             list.add(o);
			} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory.close(conn);
		return list;
	}
	
	
	
	public static List<Map<String,Object>> listMapBySql(String sql,Object... parameters){
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
	
	      PreparedStatement pst;
	      try {
			pst = conn.prepareStatement(sql);
			for(int i=0;i<parameters.length;i++){
				pst.setObject(i+1,parameters[i]);
			}
			ResultSet res=pst.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			while(res.next()){
				Map<String,Object> map=new HashMap<String,Object>();
				for(int i=1;i<=rsmd.getColumnCount();i++){
					map.put(rsmd.getColumnName(i).toUpperCase(),res.getObject(i));
				}
				list.add(map);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory.close(conn);
		return list;
	}

	public static void deleteBySql(String sql,Object...parameters){
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			for(int i=0;i<parameters.length;i++){
				pst.setObject(i+1,parameters[i]);
			}
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

