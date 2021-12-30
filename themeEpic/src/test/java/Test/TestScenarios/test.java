package Test.TestScenarios;

/***************** Header Files ******************/
import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import BaseFolder.TestScenarios.Base;
import Pages.TestScenarios.EpicPage;
import Pages.TestScenarios.HomePage;
import Pages.TestScenarios.ThemePage;

/***************** Class Test that inherits Base Class ******************/
public class test extends Base {

	/*****************
	 * Creating Objects of EpicPage, ThemePage, HomePage
	 ******************/
	EpicPage ep = new EpicPage();
	HomePage home = new HomePage();
	ThemePage theme = new ThemePage();

	/*****************
	 * 1st Test Case for Checking the Browser is Ready to Work
	 ******************/
	@Test(priority = 0)
	public void one() throws InterruptedException, IOException, AWTException {
		home.signin();
		home.navigate();
	}

	/*****************
	 * 2nd Test Case for Checking the Fuctionalities of Theme Module
	 ******************/
	@Test(priority = 1)
	public void theme() throws InterruptedException, AWTException, IOException {
		theme.navigate();
		theme.add();
		theme.comment();
		theme.ActivityLog();
		theme.Attachment();
	}

	/*****************
	 * 3rd Test Case for Checking the Fuctionalities of Epic Module
	 ******************/
	@Test(priority = 2)
	public void epic() throws InterruptedException, IOException, AWTException {
		ep.navigate();
		ep.add();
		ep.comment();
		ep.ActivityLog();
		ep.Attachment();

	}

	/*****************
	 * Closing the Browser and Saving the Reports After every Suite
	 ******************/
	@AfterSuite
	public void close() {

		report.flush();
		home.browserClose();

	}

}
