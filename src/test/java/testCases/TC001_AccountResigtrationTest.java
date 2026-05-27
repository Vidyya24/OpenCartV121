package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountResiterPage;
import pageObjects.HomePage;

public class TC001_AccountResigtrationTest extends BaseClass {
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("Test Cases Started");
		try {
		HomePage  hp= new HomePage(driver);
		hp.clickAccount();
		logger.info("Click on Account");
		hp.clickRegister();
		logger.info("Click On Register");
		
		logger.info("Customer Details");
		AccountResiterPage ar=new AccountResiterPage(driver);
		ar.setfirstname(radomString().toUpperCase());
		ar.setLastname(radomString().toUpperCase());
		ar.setemail(radomString()+"@gmail.com");
		ar.settelephone(radomnumber());
		String pass=radompassword();
		ar.setpassword(pass);
		ar.setconfirmpassword(pass);
		logger.info("Validation Details");
		ar.setprivacypolicy();
		ar.clickcontiune();
		String confirmmsg=ar.getConfirmationMsg();
		Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.info("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	}
	
}
