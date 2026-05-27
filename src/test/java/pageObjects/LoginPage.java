package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//input[@name='email']")
	WebElement txtemaillogin;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtpasslogin;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnlogin;
	
	
	public void setEmail(String email)
	{
		txtemaillogin.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		txtpasslogin.sendKeys(pwd);
	}
	
	public void clickloginbtb()
	{
		btnlogin.click();
	}
	
}
