package Pages.TestScenarios;

/***************** Header Files ******************/
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import BaseFolder.TestScenarios.Base;

/***************** Class ThemePage that inherits Base Class ******************/

public class ThemePage extends Base {

	/***************** Declaration of Variables ******************/

	FileInputStream read;
	Properties prop;
	XSSFWorkbook wb = null;
	String Name_theme = "Theme";

	/***************** Declaration of Xpaths ******************/

	public String Execute_xpath = "//a[@id='LOCK_Execute']";
	public String theme_xpath = "LOCK_Themes";
	public String add_xpath = "KEY_BUTTON_Add-btnIconEl";
	public String date_xpath = "//input[@id='CM_DUEDATE']";
	public String name_xpath = "_Text_Check_CM_Name";
	public String prio_xpath = "//*[@id=\"CM_Priority\"]";
	public String save_xpath = "SaveBtn";
	public String id_xpath = "//body[1]/form[1]/table[2]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[2]";
	public String comment_xpath = "a_1255262";
	public String comment_add__xpath = "//textarea[@name='addCommentTextArea']";
	public String commentadded_xpath = "//button[normalize-space()='Add Comment']";
	public String Acitivitylog_xpath = "a_1255265";
	public String Attachment_xpath = "a_1255263";
	public String Attachment_Added_xpath = "(//div[@id='addFile'])[1]";
	public String return_xpath = "//input[@id='CancelBtn']";
	public String click = "//input[@id='jqg_attachmentsGridTable_1']";
	public String delete = "//div[@id='attachments-tab']//div[1]//div[1]//div[1]//button[1]";

	/***************** Navigating to the Themes Page ******************/

	public void navigate() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		WebElement Menubar = driver.findElement(By.xpath(Execute_xpath));
		Thread.sleep(3000);
		action.moveToElement(Menubar).perform();
		Thread.sleep(3000);
		driver.findElement(By.id(theme_xpath)).click();
		Thread.sleep(5000);
	}

	/***************** Creating a new Theme ******************/
	public void add() throws InterruptedException, IOException {

		logger = report.createTest("Creating Theme");
		logger.log(Status.INFO, "Theme Created Sucessfully");
		Thread.sleep(5000);
		driver.findElement(By.id(add_xpath)).click();
		reportPass("Add   Button" + " - Element clicked successfully");
		driver.switchTo().frame("contentframe");

		/***************** Taking data from Excel file MP.xlsx ******************/

		read = new FileInputStream(
				"C:\\Users\\bhush\\eclipse-workspace\\themeEpic\\src\\test\\resources\\Utilittes\\MP.xlsx");

		wb = new XSSFWorkbook(
				"C:\\Users\\bhush\\eclipse-workspace\\themeEpic\\src\\test\\resources\\Utilittes\\MP.xlsx");
		XSSFSheet sheet = wb.getSheet("Theme");
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(0);
		XSSFCell cell2 = row.getCell(1);
		XSSFCell cell3 = row.getCell(2);
		String aka = cell.toString();
		String akb = cell2.toString();
	//	String prop = cell3.getStringCellValue();
		Thread.sleep(2000);
		driver.findElement(By.xpath(date_xpath)).sendKeys(aka);
		reportPass("Due Dated" + " - Added successfully");
		driver.findElement(By.id(name_xpath)).sendKeys(akb);
		reportPass(" Name Added successfully");
		Select s1 = new Select(driver.findElement(By.xpath(prio_xpath)));
		s1.selectByValue("Low");
		reportPass(" Priority Selected successfully");
		driver.findElement(By.id(save_xpath)).click();
		reportPass(" Saved Button Clicked successfully");
		Thread.sleep(5000);
		String id = driver.findElement(By.xpath(id_xpath)).getText();
	}

	/***************** Adding comment on Theme Comment Module 
	 * @throws InterruptedException ******************/
	public void comment() throws InterruptedException {
		logger = report.createTest("Creating The Theme Comment");
		logger.log(Status.INFO, "Theme Comment Created ");
		driver.findElement(By.id(comment_xpath)).click();
		driver.switchTo().frame("eform_seg_1255262");
		driver.findElement(By.xpath(comment_add__xpath)).sendKeys("Demo theme");
		reportPass("Comment  Added successfully");
		Thread.sleep(3000);
		driver.findElement(By.xpath(commentadded_xpath)).click();
		reportPass(" Add Button Clicked successfully");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");
		logger.log(Status.PASS, "Test executed " + "Comments Created");
	}

	/*****************
	 * Taking the Screenshot of Activity Log on Theme Page Module
	 ******************/
	public void ActivityLog() throws InterruptedException, IOException {

		logger = report.createTest("Automating the Activity Log");
		logger.log(Status.INFO, "Theme Activity log Created ");
		driver.findElement(By.id(Acitivitylog_xpath)).click();
		Thread.sleep(3000);
		takeScreenShot(Name_theme);
		reportPass("Screenshot Captured successfully");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");
		logger.log(Status.PASS, "Test executed " + "Acitivity Log Automates SucccessFully ");
	}

	/*****************
	 * Attaching the ScreenShot took from Acitivity Log at Theme module
	 ******************/
	public void Attachment() throws InterruptedException, AWTException, IOException {
		logger = report.createTest("Automating the Attachment");
		logger.log(Status.INFO, "Theme Attchament Created ");
		driver.findElement(By.id(Attachment_xpath)).click();
		Thread.sleep(12000);
		driver.switchTo().frame("eform_seg_1255263");

		driver.findElement(By.xpath(Attachment_Added_xpath)).click();
		reportPass("  Added  Button Clicked successfully");
		Thread.sleep(1000);

		/***************** To Automate the Windows Screen ******************/
		Robot robot = new Robot();
		Thread.sleep(2000);
		String dir = System.getProperty("user.dir");
		String pic = dir + "\\Screenshot\\" + Name_theme + ".png";
		StringSelection k = new StringSelection(pic);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(k, null);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		reportPass("  File Added");
		Thread.sleep(5000);

		/*****************
		 * Deleting the ScreenShot Added
		 ******************/

		driver.findElement(By.xpath(click)).click();
		reportPass("  Checked Box Clicked successfully");
		driver.findElement(By.xpath(delete)).click();
		reportPass("  Deleted Button Clicked successfully");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		reportPass(" Pop-up Clicked successfully");

		/*****************
		 * Attaching the ScreenShot  again After Deleting took from Acitivity Log at Theme module
		 ******************/
		Thread.sleep(5000);
		driver.findElement(By.xpath(Attachment_Added_xpath)).click();
		reportPass("  Added  Button  Clicked successfully");
		Thread.sleep(1000);

		/***************** To Automate the Windows Screen ******************/
		// Robot robot = new Robot();
		Thread.sleep(2000);
		String dir2 = System.getProperty("user.dir");
		String pic2 = dir2 + "\\Screenshot\\" + Name_theme + ".png";
		StringSelection ka = new StringSelection(pic2);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ka, null);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		reportPass("  File Added");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");
		driver.findElement(By.xpath(return_xpath)).click();
		driver.switchTo().defaultContent();
		logger.log(Status.PASS, "Test executed " + "Attachment Added SuccessFully");

		takeScreenShot("attach");
		
	}

}
