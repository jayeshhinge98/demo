package runner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

@CucumberOptions(
		  format = { 
		   // "html:reports/cucumber-html-report",
		    "json:reports/cucumber.json"
		        },features ={"src/test/resources/testRealmAPI.feature"},tags={"@DELETE,@POST,@GET"}
		   ,strict = true,glue={"step_definations"},
		   dryRun= false,monochrome = true, snippets= SnippetType.CAMELCASE) //
@RunWith(CustomCucumberRunner.class)
public class CucumberRunner{
	@AfterSuite
	public static void generateReport() throws Throwable
	{  
		File reportOutputDirectory = new File("./reports");
		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add("reports/cucumber.json");
		String buildNumber = "1";
		String projectName = "BrightTalk API Testing Report";
     	Configuration configuration = new Configuration(reportOutputDirectory, projectName);
     	configuration.setBuildNumber(buildNumber);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
	}
}
