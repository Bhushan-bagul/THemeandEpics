package Pages.TestScenarios;

/***************** Header Files ******************/
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.Status;
import BaseFolder.TestScenarios.Base;

/***************** Class Homepage that inherits Base Class ******************/
public class HomePage extends Base {

	/***************** Declaration of Xpaths ******************/

	public String project_xpath = "//*[@id=\"projectIcon\"]/ul/li[2]/a";
	public String click_xpath = "//*[@id=\"gridview-1023-record-2297\"]/tbody/tr/td[3]/div";

	/***************** Sign the User to Homepage ******************/
	public void signin() throws InterruptedException {
		invokeBrowser();
		logger = report.createTest("Opening the browser");
		logger.log(Status.INFO, "Browser opened successfully");
		Thread.sleep(3000);
		elementFind("signIn_Id").sendKeys("");
		Thread.sleep(9000);
		clickElement("submit_Id");
		elementFind("password_Id").sendKeys("");
		Thread.sleep(9000);
		clickElement("submit_Id");
		Thread.sleep(5000);
		elementFind("otp_Id").sendKeys("");
		Thread.sleep(20000);
		clickElement("verify_Xpath");
		Thread.sleep(3000);
		clickElement("confirm_Id");
		logger.log(Status.PASS, "Test executed");

	}

	/***************** Navigates to the Project Page ******************/
	public void navigate() throws InterruptedException {

		Actions action = new Actions(driver);
		WebElement Menubar = elementFind("Menu_Xpath");
		action.moveToElement(Menubar).build().perform();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.findElement(By.xpath(project_xpath)).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath(click_xpath)).click();

	}

}
