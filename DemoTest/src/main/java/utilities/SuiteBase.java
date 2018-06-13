package utilities;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SuiteBase {
	public static WebDriver chdriver = null;
	public static WebDriver iOSdriver = null;
	public static WebDriver safaridriver = null;
	public static WebDriver ffdriver = null;
	public static HtmlUnitDriver hldriver = null;
	//public static ExtentReport report=null;

	@BeforeTest
	public void setUp(){
		//setUpSafari();	
		//safaridriver.get("https://www.google.com");
		// setUpChrome();
		// chdriver.get("https://www.google.com");
		setUpHeadLessBrowser();
		hldriver.get("https://www.google.com");
		
	}

	public void setUpSafari() {
		safaridriver = new SafariDriver();
		safaridriver.manage().window().maximize();
	}
	public void setUpHeadLessBrowser() {
		hldriver = new HtmlUnitDriver();
		hldriver.setJavascriptEnabled(true);
		hldriver.manage().window().maximize();
	}

	public void setUpChrome() {
		System.setProperty("webdriver.chrome.driver", "/Users/jayesh.hinge/git/demo/DemoTest/src/main/resources/chromedriver");
		chdriver = new ChromeDriver();
		chdriver.manage().window().maximize();
	}

	public void setUpFirefox() {
		System.setProperty("webdriver.gecko.driver", "/src/main/resources/geckodriver");
		ffdriver = new FirefoxDriver();
		ffdriver.manage().window().maximize();
	}

	public void setUpIOS() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability(CapabilityType.VERSION, "6.1");
		//capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Mac");
		capabilities.setCapability("app",
				"/Users/username/Downloads/InternationalMountains   /build/Release-iphonesimulator/InternationalMountains.app");
		// iOSdriver = new IOSDriver(new URL("http://127.0.0.1:4725/wd/hub"),
		// capabilities);
		// Desktop.getDesktop().open(new File("/Applications/Appium.app"));
	}

	@AfterTest(alwaysRun = true)
	public void cleanUp() {
		 //chdriver.quit();
		// ffdriver.quit();
		//safaridriver.quit();
		// iOSdriver.quit();
		// iOSdriver.quit();
		 hldriver.quit();
	}

}
