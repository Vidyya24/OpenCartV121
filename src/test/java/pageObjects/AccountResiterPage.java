package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

	
public class AccountResiterPage extends BasePage {
		
		public AccountResiterPage(WebDriver driver)
		{
			super(driver);
		}
		
		@FindBy(xpath="//input[@name='firstname']")
		WebElement firstname;
		@FindBy(xpath="//input[@name='lastname']")
		WebElement lastname;
		@FindBy(xpath="//input[@name='email']")
		WebElement email;
		@FindBy(xpath="//input[@name='telephone']")
		WebElement telephone;
		@FindBy(xpath="//input[@name='password']")
		WebElement password ;
		@FindBy(xpath="//input[@name='confirm']")
		WebElement confirmpassword;
						@FindBy(xpath="//input[@name='agree']")
		WebElement chckplicy;
		@FindBy(xpath="//input[@value='Continue']")
		WebElement contiune_button;
		@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
		WebElement msgconfirm;
		
		
		public void setfirstname(String fname)
		{
			firstname.sendKeys(fname);
		}
		public void setLastname(String lname)
		{
			lastname.sendKeys(lname);
		}
		public void setemail(String Usremail)
		{
			email.sendKeys(Usremail);
		}
		public void settelephone(String usrtelephone)
		{
			telephone.sendKeys( usrtelephone);
		}
		public void setpassword(String usrpass)
		{
			password.sendKeys(usrpass);
		}
		public void setconfirmpassword(String conpass)
		{
			confirmpassword.sendKeys(conpass);
		}
		public void setprivacypolicy()
		{
			chckplicy.click();
		}
		public void clickcontiune()
		{
			contiune_button.click();
		}
		
		public String getConfirmationMsg() {
			try {
				return (msgconfirm.getText());
			}
			catch(Exception e)
			{
				return (e.getMessage());
			}
		}
		
		

}
