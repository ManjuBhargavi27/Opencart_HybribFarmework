package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity","Regression","Datadriven","Master"})
	@Parameters({"os","browser"})
	  public void setup(String os,String br) throws IOException {
		//loading file
		FileReader file=new FileReader(".//src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		logger=LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
			DesiredCapabilities capabilities=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("linux")) {
		        capabilities.setPlatform(Platform.LINUX);  // ✅ Added for Linux
		    } 
			else {
				System.out.println("No Operating system matched");
			}
			//browser
			switch(br.toLowerCase()) {
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
	        case "default":System.out.println("No matching browser");return;
			}
			driver =new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		switch(br.toLowerCase()) {
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		case "default":System.out.println("Invalid browser name");return;
		}}
		  
		  driver.manage().deleteAllCookies();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		  driver.get(p.getProperty("appurl"));
		  driver.manage().window().maximize();
	  }
		@AfterClass(groups= {"Sanity","Regression","Datadriven","Master"})
	 public void termDown() {
		 driver.quit();
	 }
		@SuppressWarnings("deprecation")
		public String randomString() {
			String generatedString=RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		}
		public String randomNumeric() {
			@SuppressWarnings("deprecation")
			String generatedNumber=RandomStringUtils.randomNumeric(10);
			return generatedNumber;
		}
		public String randomAlphaNumeric() {
			@SuppressWarnings("deprecation")
			String generatedString=RandomStringUtils.randomAlphabetic(5);
			@SuppressWarnings("deprecation")
			String generatedNumber=RandomStringUtils.randomNumeric(3);
			return (generatedString+generatedNumber);
		}
		 // Capture screenshot
	    public String captureScreen(String tname) throws IOException {
	        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	        File targetFile = new File(targetFilePath);

	        sourceFile.renameTo(targetFile);

	        return targetFilePath;
	    }
}
