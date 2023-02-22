package jdbc_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import dataFaker.CreateFakeData;
import utilities.MySqlUtils;
import utilities.TokenGenarate;

public class InsertUserInfoToDatabase {

	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/sql_store";
		String userName = "root";
		String passWord = "Test99%";

//	Establish a connection
		Connection conn = DriverManager.getConnection(url, userName, passWord);

//	Create JDBC Statements	
		Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

//	Execute SQL Statement
		ResultSet rs = stm.executeQuery("Select * from login_info");
		
/*
 * Get fake data from createFakeEmailAndPassword method and insert to database.
 * Generate token for each user and password by using tokenGenerate method
 * Then print all data from login_info table 
 */
	
		List<Map<Object, Object>> loginData = CreateFakeData.createFakeEmailAndPassword(3);
		System.out.println(loginData);

		for (Map<Object, Object> map : loginData) {

			for (Map.Entry m : map.entrySet()) {

				String email = m.getKey().toString();
				String password = m.getValue().toString();
				String token = TokenGenarate.tokenGenerate(email, password);
				
				rs.beforeFirst();
				rs.next();
				rs.moveToInsertRow();
				rs.updateString("email", email);
				rs.updateString("password", password);
				rs.updateString("token", token);
				rs.insertRow();
				System.out.println("updated successfull");
			}

		}
		MySqlUtils.printResultSet(rs);

//	Close connections 
		rs.close();
		stm.close();
		conn.close();

	}

}
