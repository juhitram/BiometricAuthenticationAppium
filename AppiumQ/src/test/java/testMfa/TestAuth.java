package testMfa;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.*;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TestAuth {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		//Desired capabilities
		DesiredCapabilities dc = new DesiredCapabilities();
		//dc.setCapability("deviceName", "Vivo I2202");
		/*
		 * dc.setCapability("deviceName", "smartphone");
		 * dc.setCapability("platformName", "Android");
		 * dc.setCapability("automationName", "UiAutomator2");
		 * dc.setCapability("platformVersion", "14"); dc.setCapability("appPackage",
		 * "com.android.settings"); dc.setCapability("appActivity",
		 * "com.android.settings.Settings"); dc.setCapability("noReset", "true");
		 * dc.setCapability("autoGrantPermissions", "true");
		 */
		dc.setCapability("platformName", "android");
		dc.setCapability("deviceName", "Nexus 5 API 35");
		dc.setCapability("appPackage", "com.android.settings");
		dc.setCapability("appActivity", "com.android.settings.Settings");
		dc.setCapability("automationName", "uiautomator2");
		
		
		//dc.setCapability("app", "C:\\appium\\xender-share-music-transfer-14-2-1.apk");
		//dc.setCapability("browserName", "Chrome");
	   // dc.setCapability("chromedriverExecutable","C:\\Users\\ASUS\\Desktop\\Appium\\AppiumDemo\\driver\\chromedriver-win64\\chromedriver.exe");;
		
	 // Start the Appium server
		/*
		 * AppiumDriverLocalService service = new AppiumServiceBuilder()
		 * .usingAnyFreePort() .build(); service.start();
		 */
        
		URL url = URI.create("http://127.0.0.1:4723/").toURL();
		
		AndroidDriver driver = new AndroidDriver(url,dc);
		System.out.println("application Started");
		Thread.sleep(500);
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.android.settings:id/search_action_bar_title']")).click();
		System.out.println("Clicked on Search button");
		//driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.google.android.settings.intelligence:id/open_search_view_edit_text']")).click();
		System.out.println("Clicked on Search settings");
		//driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.google.android.settings.intelligence:id/open_search_view_edit_text']"));
		Thread.sleep(500);
		WebElement search = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.google.android.settings.intelligence:id/open_search_view_edit_text']"));
		search.sendKeys("Add Fingerprint");
		Actions ac = new Actions(driver);
		
		System.out.println("Text entered");
	
		WebElement security = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Security & privacy']"));
		ac.scrollToElement(security).click().build().perform();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Security & privacy']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Device unlock']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Fingerprint & Face Unlock']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Pixel Imprint']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Add fingerprint']")).click();
		
		
		driver.quit();
		

	}

}
