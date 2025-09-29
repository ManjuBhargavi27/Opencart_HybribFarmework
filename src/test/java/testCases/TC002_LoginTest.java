package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups={"Master","Regression"})
    public void verifyLogin() {
    	logger.info("*** Started TC002_LoginTest*** ");
    	try {
    	HomePage hp=new HomePage(driver);
    	hp.clickAccount();
    	hp.clickLogin();
    	LoginPage lp=new LoginPage(driver);
    	lp.setEmail(p.getProperty("email"));
    	lp.setpwd(p.getProperty("password"));
    	lp.clickLogin();
    	MyAccountPage mp=new MyAccountPage(driver);
    	boolean targetpage=mp.isMyAccountExists();
    	Assert.assertTrue(targetpage);
    	}
    	catch(Exception e) {
    		Assert.fail();
    	}
    	logger.info("*** Ended TC002_LoginTest*** ");
    }
}
