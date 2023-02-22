package jdbc_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import utilities.MySqlUtils;

public class ConvertResultSetToListOfMap {

	public static void main(String[] args) throws SQLException {
	
		String url = "jdbc:mysql://localhost:3306/sql_store";
		String userName = "root";
		String passWord = "Test99%";

//		Establish a connection
		Connection conn = DriverManager.getConnection(url, userName, passWord);

//		Create JDBC Statements	
		Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

//		Execute SQL Statement
		ResultSet rs = stm.executeQuery("Select * from customers");
//		Get ResultSet
		
		List<Map<Object, Object>> rsSet = MySqlUtils.convertResultSetToListOfMap(rs);
		
		for (Map<Object, Object> map : rsSet) {
			System.out.println(map);
		}

	}

}
