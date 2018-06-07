package utilities;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.ios.IOSDriver;

public class SuiteBase {
	public static WebDriver driver=null;
	public static IOSDriver iOSdriver=null;
	
	@BeforeTest
	public void setUp() throws IOException {
		System.setProperty("webdriver.gecko.driver","path to geckodriver.exe");
		System.setProperty("webdriver.chrome.driver","path to chromedriver.exe");
		driver=new ChromeDriver();
	//	driver=new FirefoxDriver();
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability(CapabilityType.VERSION, "6.1");
		capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Mac");
		capabilities.setCapability("app","/Users/username/Downloads/InternationalMountains   /build/Release-iphonesimulator/InternationalMountains.app");
		iOSdriver = new IOSDriver(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);
		//Desktop.getDesktop().open(new File("/Applications/Appium.app"));

	}
	
	@AfterTest(alwaysRun=true)
	public void cleanUp() {
		driver.quit();
		iOSdriver.quit();
//		iOSdriver.quit();	
//		testing
	}
	
}

