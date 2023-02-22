package jdbc_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import utilities.MySqlUtils;
import utilities.TokenGenarate;

public class TestLogin {

	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/sql_store";
		String userName = "root";
		String passWord = "Test99%";

//		Establish a connection
		Connection conn = DriverManager.getConnection(url, userName, passWord);

//		Create JDBC Statements	
		Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

//		Execute SQL Statement
		ResultSet rs = stm.executeQuery("Select * from login_info");
//		Get ResultSet
		
		MySqlUtils.printResultSet(rs);

		/*
		 * email=azucena.wisoky@yahoo.com, password=7acb47mkyejo5m, token=+UWV(+a_mY^c)a
		 * email=johana.boehm@hotmail.com, password=mkx7y09q3wr, token=WUb!c#[ a\
		 */

		String email = "tristan.howe@hotmail.com";
		String password = "62pteykk";
		boolean isLoginSuccessful = false;

		List<Map<Object, Object>> loginInfo = MySqlUtils.convertResultSetToListOfMap(rs);

		for (Map<Object, Object> map : loginInfo) {

			if (TokenGenarate.tokenGenerate(email, password).equals(map.get("token"))) {
				isLoginSuccessful = true;
				System.out.println("Login successful");
				break;
			}

		}
		
		if(isLoginSuccessful==false) {
			
			System.out.println("Invalid Credential");
		}

	}

}
