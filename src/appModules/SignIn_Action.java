package appModules;
import org.openqa.selenium.WebDriver;
import pageObjects.LogIn_Page;
import utility.ExcelUtils;
// Now this method does not need any arguments
public class SignIn_Action {
	public static void Execute(WebDriver driver) throws Exception{
		// This is to get the values from Excel sheet, passing parameters (Row num & amp; Col num) to getCellData method
		String sEmail = ExcelUtils.getCellData(1, 1);
		String sPassword = ExcelUtils.getCellData(1,2);
		LogIn_Page.txtbx_UserName(driver).sendKeys(sEmail);
		LogIn_Page.txtbx_Password(driver).sendKeys(sPassword);
		LogIn_Page.btn_LogIN(driver).click();
	}
}
