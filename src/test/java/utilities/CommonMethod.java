package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.Scenario;

public class CommonMethod extends PageInitializer {

	public static void main(String[] args) {
		System.out.println(getTimeStemp());
	}

	public static String getTimeStemp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return sdf.format(date.getTime());
	}

	public static void takeScreenshot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			File screenshot = ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("screenShot/" + getTimeStemp() + scenario.getName() + ".png"));
		}
	}
	/*
	 * Set cucumber.publish.enabled=true
	 */
	public static void attachScreenshotToReport(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs(OutputType.BYTES);
		    scenario.attach(screenshot, "image/png", scenario.getName()); 
		}
	}
}
