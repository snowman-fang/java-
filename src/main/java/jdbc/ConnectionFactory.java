package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionFactory extends Properties {
	private static ConnectionFactory instance;
	private static Properties prop;
	private static List<Connection> conns;
	public static synchronized  ConnectionFactory  getInstance(){
		if (instance == null) {
			instance = new ConnectionFactory();
			conns = new ArrayList<Connection>();
			prop = new Properties();
			try {
				prop.put("Driver","com.mysql.cj.jdbc.Driver");
				prop.put("Url","jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
				prop.put("username","root");
				prop.put("password","123456");
				Class.forName(prop.getProperty("Driver"));
				for (int i = 0; i < 10; i++) {
					Connection	conn = DriverManager.getConnection(prop.getProperty("Url"), prop.getProperty("username"),prop.getProperty("password"));
					conns.add(conn);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	public Connection getConnection(){
		return conns.remove(0);
	}
	public void close(Connection conn){
		if(conn!=null){
			conns.add(conn);
		}
	}
}
