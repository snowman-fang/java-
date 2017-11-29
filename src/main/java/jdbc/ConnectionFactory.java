package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionFactory extends Properties {
	private static Properties prop;
	private static  String CONFIGNAME = "jdbc.properties";
	private static List<Connection> conns;
	private Connection conn;
	public ConnectionFactory(){
		conns = new ArrayList<Connection>();
		prop = new Properties();
		try {
			//prop.load(ConnectionFactory.class.getResourceAsStream(CONFIGNAME));
			prop.put("Driver","com.mysql.jdbc.Driver");
			prop.put("Url","jdbc:mysql://localhost:3306/test?characterEncoding=utf-8");
			prop.put("username","root");
			//prop.put("password","");
			Class.forName(prop.getProperty("Driver"));
			for (int i = 0; i < 10; i++) {
				conn = DriverManager.getConnection(prop.getProperty("Url"), prop.getProperty("username"),prop.getProperty("password"));
				conns.add(conn);
				}
		}/* catch (IOException e) {
			e.printStackTrace();
		}*/ catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
