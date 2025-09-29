package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="Datadriven") // getting data provider from different class
    public void verify_loginDDT(String email, String pwd, String exp) {
    	logger.info("*** TC003_LoginDDT*** ");
    	try {
        // HomePage
        HomePage hp = new HomePage(driver);
        hp.clickAccount();
        hp.clickLogin();

        // Login
        LoginPage lp = new LoginPage(driver);
        lp.setEmail(email);
        lp.setpwd(pwd);
        lp.clickLogin();

        // MyAccount
        MyAccountPage macc = new MyAccountPage(driver);
        boolean targetPage = macc.isMyAccountExists();

        if (exp.equalsIgnoreCase("Valid")) {
            if (targetPage == true) {
                macc.clickLogout();
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }

        if (exp.equalsIgnoreCase("Invalid")) {
            if (targetPage == true) {
                macc.clickLogout();
                Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }
        }}catch(Exception e) {
        	Assert.fail();
        }
        logger.info("*** Finshed TC003_LoginDDT*** ");
    }
}
