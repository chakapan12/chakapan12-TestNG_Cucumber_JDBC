package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {
				"html:target/html_report/html_cucumber_reports.html",
				"json:target/json_report/json_cucumber_report.json"
		},
	features = "src/test/resources/features/register.feature",
	glue = "stepDefinitions",
	dryRun = false)
//	tags = "@InvalidEmailWithExcel") //@InValidCredentialsWithExcelFile")

public class CucumberRunnerTest extends AbstractTestNGCucumberTests{
	
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
