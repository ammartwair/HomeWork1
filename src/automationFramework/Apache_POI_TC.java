package automationFramework;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import pageObjects.*;
import utility.Constant;
import utility.ExcelUtils;
import appModules.SignIn_Action;
public class Apache_POI_TC {
	private static WebDriver driver = null;
	public static void main(String[] args) throws Exception{
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method
	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(Constant.URL);
	try {
		driver.findElement(By.cssSelector("a.logo-home"));
		System.out.println("The logo is displayed");
	}catch(Exception e) {
		System.out.println("The logo isn't displayed");
	}
	try {
		WebElement ele = driver.findElement(By.cssSelector("a.toggle-menu"));
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
		driver.findElement(By.xpath("/html/body/div[2]/header/nav/div[1]/div/div/div/div[3]/ul/li/ul/li[1]/a")).click();
		System.out.println("SignIn/Register link is clicked");
	}
	catch (Exception e) {
		System.out.println("The SignIn/Register link has not been found");
	}
	try {
		String text = driver.findElement(By.cssSelector("h1#loginTabHeader")).getText();
		if(text.equals("Returning Customers")) {
		System.out.println("The user is navigated to sign in page correctly.");
		}else{
		System.out.println("The user isn't navigated to sign in page correctly.");
		}
	}
	catch (Exception e) {
		System.out.println("The user is not navigated to sign in page correctly.");
	}
	SignIn_Action.Execute(driver);
	try {
		String text = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div[1]/div/div/div/div[3]/ul/li/a/span[2]")).getText();
		if(text.equals("My Account")){
		System.out.println("The user is logged in correctly.");
		}else{
		System.out.println("The user isn't logged in correctly.");
		}
	}
	catch (Exception e) {
		System.out.println("The user isn't logged in correctly.");
	}
	try {
		String text = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div")).getText();
		if(text.equals("Welcome, "+ExcelUtils.getCellData(1,3))) {
		System.out.println("Welcome, "+ExcelUtils.getCellData(1,3) +" is displayed correctly.");
		}else{
		System.out.println("Welcome, "+ExcelUtils.getCellData(1,3) +" is not displayed correctly.");
		}
	}
	catch (Exception e) {
		System.out.println("Welcome, "+ExcelUtils.getCellData(1,3) +" is not displayed correctly.");
	}	
	System.out.println("Login Successfully, now it is the time to Log Off buddy.");
	driver.quit();
	// This is to send the PASS value to the Excel sheet in the result column.
	ExcelUtils.setCellData("Pass", 1, 4);
	}
}
