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

/***************** Class EpicPage that inherits Base Class ******************/
public class EpicPage extends Base {

	/***************** Declaration of Variables ******************/
	FileInputStream read;
	Properties prop;
	XSSFWorkbook wb = null;
	String name = "Epic";

	/***************** Declaration of Xpaths ******************/
	public String Execute_xpath = "//a[@id='LOCK_Execute']";
	public String Epic_xpath = "//a[@id='LOCK_Epics']";
	public String Add_xpath = "KEY_BUTTON_Add-btnIconEl";
	public String date_xpath = "//input[@id='CM_DUEDATE']";
	public String name_xpath = "_Text_Check_CM_Name";
	public String prio_xpath = "//select[@id='THEME']";
	public String save_xpath = "SaveBtn";
	public String id_xpath = "/html/body/form/table[2]/tbody/tr/td/div[1]/table/tbody/tr/td/table/tbody/tr[1]/td[2]";
	public String comment_xpath = "a_1255227";
	public String comment_add__xpath = "textarea[name='addCommentTextArea']";
	public String commentadded_xpath = "//button[normalize-space()='Add Comment']";
	public String Acitivitylog_xpath = "a_1255230";
	public String Attachment_xpath = "a_1255228";
	public String Attachment_Added_xpath = "(//div[@id='addFile'])[1]";
	public String click = "//input[@id='jqg_attachmentsGridTable_1']";
	public String delete ="//button[@id='deleteFile']";

	/***************** Navigating to the Epics Page ******************/
	public void navigate() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		WebElement Menubar = driver.findElement(By.xpath(Execute_xpath));
		Thread.sleep(6000);
		action.moveToElement(Menubar).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Epic_xpath)).click();
		Thread.sleep(2000);
	}

	/***************** Creating a new Epic ******************/
	public void add() throws InterruptedException, IOException {
		logger = report.createTest("Creating The Epic");
		logger.log(Status.INFO, "Epic Created ");
		Thread.sleep(5000);
		driver.findElement(By.id(Add_xpath)).click();
		reportPass("Add   Button" + " - Element clicked successfully");
		Thread.sleep(2000);
		driver.switchTo().frame("contentframe");
		/***************** Taking data from Excel file MP.xlsx ******************/
		read = new FileInputStream(
				"C:\\Users\\bhush\\eclipse-workspace\\themeEpic\\src\\test\\resources\\Utilittes\\MP.xlsx");

		wb = new XSSFWorkbook(
				"C:\\Users\\bhush\\eclipse-workspace\\themeEpic\\src\\test\\resources\\Utilittes\\MP.xlsx");
		XSSFSheet sheet = wb.getSheet("Epic");
		XSSFSheet sheet2 = wb.getSheet("Theme");
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(0);
		XSSFCell cell2 = row.getCell(1);
		String aka = cell.toString();
		String akb = cell2.toString();
		driver.findElement(By.xpath(date_xpath)).sendKeys(aka);
		reportPass("Due Dated" + " - Added successfully");
		driver.findElement(By.id(name_xpath)).sendKeys(akb);
		reportPass(" Name Added successfully");
		Select k = new Select(driver.findElement(By.xpath(prio_xpath)));
		k.selectByVisibleText("26decTheme");
		reportPass("Theme Selected SuccesFully");
		driver.findElement(By.id(save_xpath)).click();
		reportPass("Save" + " - Element clicked successfully");
		Thread.sleep(4000);
		String id = driver.findElement(By.xpath(id_xpath)).getText();
		logger.log(Status.PASS, "Test executed " + " Epic id:- " + id);
	}

	/***************** Adding comment on Epic Comment Module 
	 * @throws InterruptedException ******************/
	public void comment() throws InterruptedException {
		logger = report.createTest("Creating The Epic Comment");
		logger.log(Status.INFO, "Epic Comment Created ");
		driver.findElement(By.id(comment_xpath)).click();
		driver.switchTo().frame("eform_seg_1255227");
		driver.findElement(By.cssSelector(comment_add__xpath)).sendKeys("Epic Demo");
		Thread.sleep(2000);
		reportPass("Comment  Added successfully");
		driver.findElement(By.xpath(commentadded_xpath)).click();
		Thread.sleep(2000);
		reportPass(" Add Button Clicked successfully");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");
		logger.log(Status.PASS, "Test executed " + "Comments Created");
	}

	/*****************
	 * Taking the Screenshot of Activity Log on Epic Page Module
	 ******************/
	public void ActivityLog() throws InterruptedException, IOException {
		logger = report.createTest("Automating the Activity Log");
		logger.log(Status.INFO, "Epic Activity log Created ");
		driver.findElement(By.id(Acitivitylog_xpath)).click();
		Thread.sleep(3000);
		takeScreenShot(name);
		reportPass("Screenshot Captured successfully");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");
		logger.log(Status.PASS, "Test executed " + "Acitivity Log Automates SucccessFully ");
	}

	/*****************
	 * Attaching the ScreenShot took from Acitivity Log at Epic module
	 ******************/
	public void Attachment() throws InterruptedException, AWTException, IOException {
		logger = report.createTest("Automating the Attachment");
		logger.log(Status.INFO, "Epic Attchament Created ");
		driver.findElement(By.id(Attachment_xpath)).click();
		Thread.sleep(10000);
		driver.switchTo().frame("eform_seg_1255228");
		driver.findElement(By.xpath(Attachment_Added_xpath)).click();
		reportPass("  Added  Button Clicked successfully");
		Thread.sleep(1000);
		/***************** To Automate the Windows Screen ******************/
		Robot robot = new Robot();
		Thread.sleep(2000);
		String dir = System.getProperty("user.dir");
		String pic = dir + "\\Screenshot\\" + name + ".png";
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
		
		/*****************
		 * Deleting the ScreenShot Added
		 ******************/
		
		Thread.sleep(5000);
		driver.findElement(By.xpath(click)).click();
		reportPass("  Checked Box Clicked successfully");
		driver.findElement(By.xpath(delete)).click();
		reportPass("  Deleted Button Clicked successfully");
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		reportPass(" Pop-up Clicked successfully");
		
		Thread.sleep(5000);
		
		/*****************
		 * Attaching the ScreenShot  again After Deleting took from Acitivity Log at Epic module
		 ******************/
		
		driver.findElement(By.xpath(Attachment_Added_xpath)).click();
		reportPass("  Added  Button Clicked successfully");
		Thread.sleep(1000);
		/***************** To Automate the Windows Screen ******************/
		
		Thread.sleep(2000);
		String dir2 = System.getProperty("user.dir");
		String pic1 = dir2 + "\\Screenshot\\" + name + ".png";
		StringSelection k1 = new StringSelection(pic1);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(k1, null);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		reportPass("  File Added");
		
		Thread.sleep(15000);
		logger.log(Status.PASS, "Test executed " + "Attachment Added SuccessFully");
		takeScreenShot("attach");
	}

}
