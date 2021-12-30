package Utils;

/***************** Header Files ******************/
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/***************** Class ExtendReportManager ******************/
public class ExtentReportManager {

	/***************** Declaring the Variable ******************/
	public static ExtentReports report;

	/***************** gets the Report ******************/
	public static ExtentReports getReportInstance() {

		if (report == null) {

			String reportName = DateUtils.getTimeStamp() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "/test-output/" + reportName);
			
			htmlReporter.config().setDocumentTitle("BookMyShow Automation");
			
			
			htmlReporter.config().setReportName("The Heathers");
			htmlReporter.config().setTimeStampFormat("MM dd,yyyy HH:mm:ss");
			
						htmlReporter.config().setTheme(Theme.DARK);
			
					
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Browser", "Chrome");
		//	htmlReporter.config().setDocumentTitle("Test scenario Automation Results");
			//htmlReporter.config().setReportName("Test scenario Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		//	htmlReporter.config().setTimeStampFormat("MM dd,yyyy HH:mm:ss");
		}
		return report;
	}
}
