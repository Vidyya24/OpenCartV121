package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_VerifyLogin extends BaseClass {
	
	
	@Test(dataProvider="LoginData" , dataProviderClass=DataProviders.class,groups="datadriven")//getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp) {
		
	logger.info("*********************Starting TC003_VerifyLogin");
	System.out.println("new");
	
	try
	{
	HomePage hp=new HomePage(driver);
	hp.clickAccount();
	hp.clickLogin();
	
	//Login
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(pwd);
	lp.clickloginbtb();
	
	//MyAccount
	MyAccountPage macc= new MyAccountPage(driver);
	boolean tragetpage=macc.isMyAccountPageExists();
	
	/*Data is valid - login success - test pass - logout
	 * 				  login failed - test fail 
	 * Data is invalid  - login success - test fail -logout
	 * 					  login failed -test pass
	 */
	
	if(exp.equalsIgnoreCase("Valid"))
	{
		if(tragetpage==true)
		{
			macc.logout();
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
		
	}
	if(exp.equalsIgnoreCase("Invalid"))
	{
		if(tragetpage==true)
		{
			macc.logout();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
	}
	}catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("*********************Finised TC003_VerifyLogin");
}
}