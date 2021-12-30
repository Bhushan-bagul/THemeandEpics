package BaseFolder.TestScenarios;

/***************** Header Files ******************/
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Utils.DateUtils;
import Utils.ExtentReportManager;

/***************** Base Class ******************/
public class Base {

	// Declaration of Variable
	public static WebDriver driver;
	public static WebElement element;
	protected static Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	/***************** Constructor ******************/
	public Base() {
		try {
			this.driver = driver;
			String userDir = System.getProperty("user.dir");
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					userDir + "\\src\\test\\resources\\Utilittes\\ProjectConfig.properties");
			prop.load(ip);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	/***************** Invoking the Browser ******************/
	public void invokeBrowser() {
		String userDir = System.getProperty("user.dir");
		String browserName = prop.getProperty("browser1");
		// opening browser
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\bhush\\eclipse-workspace\\2063478_ThemesAndEpics\\themeEpic\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", userDir + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			// options.addPreference("dom.webnotifications.enabled",false);
			driver = new FirefoxDriver(options);
		}

		driver.get(prop.getProperty("websiteURL"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		// driver.close();

	}

	/***************** To click element ******************/
	public void clickElement(String xpathKey) {
		try {
			elementFind(xpathKey).click();
			reportPass(xpathKey + " - Element clicked successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/***************** To locate the element ******************/
	public WebElement elementFind(String locatorKey) {
		WebElement element = null;
		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_LinkTest")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorKey)));

			} else {
				reportFail("Failing the TestCase,Invalid Locator " + locatorKey);
				Assert.fail("Failing the TestCase,Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {
			// fail the test case and generate report
			reportFail(e.getMessage());
			e.printStackTrace();
			Assert.fail("failing the TestCase :" + e.getMessage());
		}

		return element;
	}

	/***************** Report function fail ******************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShot("Fail");
		Assert.fail(reportString);
	}

	/***************** Report function Pass ******************/
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/***************** Screenshot of search ******************/
	public void takeScreenShot(String name) {

		TakesScreenshot takescreenshot = (TakesScreenshot) driver;

		File soursceFile = takescreenshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "\\Screenshot\\" + name + ".png");

		try {
			FileUtils.copyFile(soursceFile, destFile);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\Screenshot\\" + name + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/***************** Close browser ******************/
	public void browserClose() {

		driver.quit();
	}
}
