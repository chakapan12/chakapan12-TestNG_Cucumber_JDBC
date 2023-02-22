package jdbc_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {

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

		while (rs.next()) {
			int customerID = rs.getInt("customer_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");

			System.out.println(customerID + " : " + firstName + " " + lastName);
			System.out.println();
		}

//		Close connections 
		rs.close();
		stm.close();
		conn.close();

	}

}
