package stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.BaseClass;
import utilities.CommonMethod;
import utilities.ExcelUtils;

public class Hook {
	
	Logger log = LogManager.getLogger(Hook.class);
	public static List<Map<Object, Object>> logInData;
	public static List<Map<Object, Object>> regData;
	
	public static void main(String[] args) {
		
		regData = ExcelUtils.excelIntoListOfMap(BaseClass.getProperty("regExcelPath"), BaseClass.getProperty("regExcelSheet"));
		System.out.println(regData);
		
	}
	
	@Before(value= "@InValidCredentialsWithExcelFile or @InvalidEmailWithExcel", order=1)
	public void setUpData() {
		log.info("Loading data from excel");
		logInData = ExcelUtils.excelIntoListOfMap(BaseClass.getProperty("loginExcelPath"), BaseClass.getProperty("loginExcelSheet"));
		regData = ExcelUtils.excelIntoListOfMap(BaseClass.getProperty("regExcelPath"), BaseClass.getProperty("regExcelSheet"));
	}
	
	
	
	@Before(order=2)
	public void setUp() {
		log.info("Setting up driver");
		BaseClass.setDriver(BaseClass.getProperty("browser"));
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {
		log.info("Closing browser");
		CommonMethod.takeScreenshot(scenario);
//		CommonMethod.attachScreenshotToReport(scenario);
		BaseClass.tearDown();

	}

}
