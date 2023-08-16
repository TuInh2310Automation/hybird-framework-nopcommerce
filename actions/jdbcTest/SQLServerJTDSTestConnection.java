package jdbcTest;

import static org.mockito.ArgumentMatchers.intThat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerJTDSTestConnection {
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
		return SQLServerJTDSConnUtils.getSQLServerConnection();
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		// TODO Auto-generated method stub
		System.out.println("Get connection...");
		// Lấy ra đối tượng Connection kết nối vào db
		Connection conn= SQLServerJTDSConnUtils.getSQLServerConnection();
		System.out.println("opened connection " + conn);
		Statement statement=conn.createStatement();
		String sql = "Select * From automationtest.dbo.BRANCH bra;";
		String insertSQL="Insert into automationtest.dbo.BRANCH ([ADDRESS],[CITY],[NAME],[STATE],[ZIP_CODE]) VALUES ('40 TAY SON','hn','van anh','HN','10010');";
	
		String paramSQL="Select emp.EMP_ID, emp.FIRST_NAME, emp.TITLE, emp.DEPT_ID from automationtest.dbo.EMPLOYEE emp where emp.TITLE like ? and emp.DEPT_ID = ?;";
		PreparedStatement pstm=conn.prepareStatement(paramSQL);
		pstm.setString(1, "%ent");
		pstm.setInt(2, 3);
		// Thuc thi câu lệnh SQl trả về đối tượng ResultSet
		//int rowCount= statement.executeUpdate(insertSQL);
		//System.out.println("Row effect " + rowCount);
		//ResultSet rs= statement.executeQuery(sql);
		ResultSet rs= pstm.executeQuery();
		while (rs.next()) {
			// Di chuyển con trỏ xuống bản ghi kế tiếp
			
			String EMP_ID=rs.getString("EMP_ID");
			String FIRST_NAME=rs.getString("FIRST_NAME");
			System.out.println("EMP_ID " +EMP_ID +" NAME "+ FIRST_NAME);
		}
		// đóng kết nối
		conn.close();
		System.out.println("close connection");
	}

}
