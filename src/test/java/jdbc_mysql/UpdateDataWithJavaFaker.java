package jdbc_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.github.javafaker.Faker;

public class UpdateDataWithJavaFaker {

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

//		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());

		Faker fake = new Faker();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		rs.beforeFirst();
		
		int numberOfData = 1;
		
		for (int i = 1; i <= numberOfData; i++) {
			rs.next();
			rs.updateString("first_name", fake.name().firstName());
			rs.updateString("last_name", fake.name().lastName());
			rs.updateString("birth_date", sdf.format(fake.date().birthday()));
			rs.updateString("address", fake.address().streetAddress());
			rs.updateString("city", fake.address().city());
			rs.updateString("state", fake.address().stateAbbr());
			rs.updateRow();;
			System.out.println("updated row : " + i + " successfull");
		}

//		Close connections 
		rs.close();
		stm.close();
		conn.close();

	}

}
