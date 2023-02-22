package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MySqlUtils {
	
	/*
	 * Establish connection between jdbc and mySql
	 */
	
	public static Connection createConnection(String url, String userName, String passWord) throws SQLException {
	
		Connection conn = DriverManager.getConnection(url, userName, passWord);
		
		return conn;
	}
	
	/*
	 * print result set to console with formating width of each column
	 */
	public static void printResultSet(ResultSet rs) throws SQLException {

		ResultSetMetaData md = rs.getMetaData();

		List<Integer> eachColumWith = findMaxWidthEachColummn(rs);

		rs.last();

		int rowCount = rs.getRow();

		rs.beforeFirst();

		int width = 0;
		String format;
		for (int row = 0; row < rowCount; row++) {

			rs.next();

			if (row == 0) {

				for (int i = 1; i <= md.getColumnCount(); i++) {
					width = eachColumWith.get(i - 1) + 3;
					format = "%-" + width + "s";
					System.out.printf(format, md.getColumnName(i));

				}
				System.out.println();
			}

			for (int i = 1; i <= md.getColumnCount(); i++) {
				width = eachColumWith.get(i - 1) + 3;
				format = "%-" + width + "s";
				System.out.printf(format, rs.getString(i));
			}
			System.out.println();

		}

	}

	/*
	 * find max with of each column and store in List<Integer>
	 */
	public static List<Integer> findMaxWidthEachColummn(ResultSet rs) throws SQLException {

		ResultSetMetaData md = rs.getMetaData();

		rs.last();
		int rowCount = rs.getRow();

		rs.beforeFirst();

		List<Integer> maxWidth = new ArrayList<>();

		rs.next();

		int max = 0;
		int tempMax = 0;
		for (int i = 1; i <= md.getColumnCount(); i++) {
			max = md.getColumnName(i).length();
			for (int j = 1; j <= rowCount; j++) {
				try {
					tempMax = rs.getString(i).length();
				} catch (Exception e) {
					tempMax = 0;
				}
				if (tempMax > max) {
					max = tempMax;
				}
				rs.next();
			}
			int temp = max;
			maxWidth.add(temp);
			rs.beforeFirst();
			rs.next();
		}
		return maxWidth;

	}
	
	/*
	 * convert result set to List of Map
	 */
	public static List<Map<Object, Object>> convertResultSetToListOfMap(ResultSet rs) throws SQLException {

		List<Map<Object, Object>> rsMap = new ArrayList<>();

		ResultSetMetaData md = rs.getMetaData();

		rs.last();
		int rowCount = rs.getRow();
		int index = 0;
		rs.beforeFirst();

		for (int row = 1; row <= rowCount; row++) {
			rs.next();
			Map<Object, Object> map = new LinkedHashMap<>();
			rsMap.add(map);

			for (int i = 1; i <= md.getColumnCount(); i++) {
				rsMap.get(index).put(md.getColumnName(i), rs.getString(i));
			}
			index++;

		}

		return rsMap;
	}

}
