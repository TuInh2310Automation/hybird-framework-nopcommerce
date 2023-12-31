package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySQLConnUtils {
	public static Connection getMySQLConnection() {
		String hostName = "localhost";
		String dbName = "automationfc";
		String userName = "root";
		String password = "";
		return getMySQLConnection(hostName, dbName, userName, password);
	}

	private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Cấu trúc url connection dành cho mysql
					String connectionURL= "jdbc:mysql://" + hostName+":3306/"+ dbName;
					conn=DriverManager.getConnection(connectionURL,userName,password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
}
