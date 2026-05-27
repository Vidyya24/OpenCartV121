package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_MyLoginTest extends BaseClass {
	
	@Test(groups={"sanity" , "Master"})
	public void verify_login()
	{
		try {
		logger.info("**********Stsrting TC_002_Login Test");
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.clickAccount();
		hp.clickLogin();
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickloginbtb();
		
		//MyAccount
		MyAccountPage macc= new MyAccountPage(driver);
		boolean tragetpage=macc.isMyAccountPageExists();
		Assert.assertEquals(tragetpage, true);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**********Finished TC_002_Login Test");
				
	}
	

}
