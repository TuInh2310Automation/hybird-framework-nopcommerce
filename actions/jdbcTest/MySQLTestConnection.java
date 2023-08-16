package jdbcTest;

import static org.mockito.ArgumentMatchers.intThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class MySQLTestConnection {
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
		return MySQLConnUtils.getMySQLConnection();
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Get connection...");
		// Lấy ra đối tượng Connection kết nối vào db
		Connection conn= MySQLTestConnection.getMyConnection();
		System.out.println("opened connection " + conn);
		Statement statement=conn.createStatement();
		String sqlString = "Select Emp.Emp_Id, Emp.First_Name,Emp.Last_Name, Emp.Dept_Id From Employee Emp";
		// Thuc thi câu lệnh SQl trả về đối tượng ResultSet
		
		ResultSet rs= statement.executeQuery(sqlString);
		while (rs.next()) {
			// Di chuyển con trỏ xuống bản ghi kế tiếp
			int empID=rs.getInt(1);
			String empFirstName=rs.getString(2);
			String empLastName=rs.getString(3);
			System.out.println("Emp ID " + empID);
		}
		// đóng kết nối
		conn.close();
		System.out.println("close connection");
		
		
		
	}
}
