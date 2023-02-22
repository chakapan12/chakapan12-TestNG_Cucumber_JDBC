package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFWorkbook book;
	private static Sheet sheet;

	// open file and load book
	private static void openExcel(String filePath) {

		try {
			FileInputStream files = new FileInputStream(filePath);
			book = new XSSFWorkbook(files);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// load the worksheet
	private static void loadSheet(String sheetName) {
		sheet = book.getSheet(sheetName);
	}

	// row count
	private static int rowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

// col count
	private static int colCount() {
		return sheet.getRow(0).getLastCellNum();
	}

	private static Object cellData(int rowIndex, int colIndex) {

		try {

			Cell cell = sheet.getRow(rowIndex).getCell(colIndex);

			switch (cell.getCellType()) {

			// Case 1
			case NUMERIC:
				return cell.getNumericCellValue();

			// Case 2
			case STRING:
				return cell.getStringCellValue();

			default:
				return "";
			}
		} catch (Exception e) {
			return "";
		}

	}

	public static List<Map<Object, Object>> excelIntoListOfMap(String filePath, String sheetName) {
		openExcel(filePath);
		loadSheet(sheetName);
		int rows = rowCount();
		int cols = colCount();

		List<Map<Object, Object>> myData = new ArrayList<>();

		for (int i = 1; i < rows; i++) {
			Map<Object, Object> map = new LinkedHashMap<>();
			myData.add(map);
			for (int j = 0; j < cols; j++) {
				myData.get(i - 1).put(cellData(0, j), cellData(i, j));
			}
		}
		return myData;

	}

	public static Object[][] excelIntoArray(String filePath, String sheetName) {
		openExcel(filePath);
		loadSheet(sheetName);
		int rows = rowCount();
		int cols = colCount();

		Object[][] data = new Object[rows - 1][cols];

		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				data[i - 1][j] = cellData(i, j);
			}
		}
		return data;

	}


}
