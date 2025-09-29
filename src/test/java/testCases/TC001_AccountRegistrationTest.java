package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	
 @Test(groups={"Sanity","Master"})
 public void verify_account_registration() throws InterruptedException {
		logger.info("****Started TC001_AccountRegistrationTest Test case");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickAccount();
		logger.info("*Clicked on Account*");
		hp.clickRegister();
		logger.info("*Clicked on Register*");
		AccountRegistrationPage ap= new AccountRegistrationPage(driver);
		logger.info("*Providing User details*");
		ap.setFirstname(randomString().toUpperCase());
		ap.setLastname(randomString().toUpperCase());
		ap.setEmail(randomString()+"@gmail.com");
		ap.setTelephone(randomNumeric());
		String pasword=randomAlphaNumeric();
		ap.setPassword(pasword);
		ap.setConfirmPassword(pasword);
		ap.setPrivacypolocy();
		ap.clickButton();
		logger.info("*Validating expected meesage*");
		String cnfrmmsg=ap.getConfirmationmsg();
		if(cnfrmmsg.equals("Your Account Has Been Created!")) {
		Assert.assertTrue(true);
		}
		else 
		{ 
			logger.error("*Test failed*");
			logger.debug("*debug logs");
			Assert.assertTrue(false);
		}
		
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*Test case finshed*");
 }
	
}
