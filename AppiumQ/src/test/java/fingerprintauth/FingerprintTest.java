package fingerprintauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class FingerprintTest {
	WebDriver driver;
	private String securityText = "//android.widget.TextView[@text='Security']";
	private String touchSensorText = "//android.widget.TextView[@text='Touch the sensor']";
	private String doneText = "//android.widget.Button[@text='DONE']";

	@org.junit.Test
	public void test1() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "android");
		caps.setCapability("deviceName", "Nexus 5 API 35");
		caps.setCapability("appPackage", "com.android.settings");
		caps.setCapability("appActivity", "com.android.settings.Settings");
		caps.setCapability("automationName", "uiautomator2");

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			Thread.sleep(5000);
			//swipeUpWithoutTouchAction(driver);

			
			/*
			 * TouchAction action = new TouchAction(((AndroidDriver)driver)); int height =
			 * driver.manage().window().getSize().height; int width =
			 * driver.manage().window().getSize().width;
			 * System.out.println("Dimensions of screen ->  " + width + " x " + height);
			 * action.press(new PointOption<PointOption<T>>().point(94, height - 1000))
			 * .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
			 * .moveTo(new PointOption().point(94, 400)).release(); MultiTouchAction
			 * multiAction = new MultiTouchAction(((AndroidDriver<AndroidElement>)driver));
			 * multiAction.add(action).perform(); Thread.sleep(5000);
			 */
			 

			//WaitUntilElementIsDisplayed(By.xpath("(//android.widget.TextView[@content-desc='Settings'])[1]"));
			//driver.findElement(By.xpath("(//android.widget.TextView[@content-desc='Settings'])[1")).click();
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.android.settings:id/search_action_bar_title']")).click();
			driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.google.android.settings.intelligence:id/open_search_view_edit_text']")).click();
			driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.google.android.settings.intelligence:id/open_search_view_edit_text']")).sendKeys("Add Fingerprint");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Add fingerprint']]")).click();
			/*
			 * driver.findElement(By.
			 * xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Add fingerprint']"
			 * )).click();
			 * 
			 * driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
			 * 
			 * WebElement pin = driver.findElement( By.xpath(
			 * "//android.widget.EditText[@resource-id='com.android.settings:id/password_entry']"
			 * )); pin.sendKeys("1234");
			 * driver.findElement(By.xpath("//android.widget.Button[@text='NEXT']")).click()
			 * ; Thread.sleep(2000); pin.sendKeys("1234");
			 * driver.findElement(By.xpath("//android.widget.Button[@text='CONFIRM']")).
			 * click();
			 * driver.findElement(By.xpath("//android.widget.Button[@text='DONE']")).click()
			 * ; WaitUntilElementIsDisplayed(By.xpath(touchSensorText));
			 */

			for (int i = 1; i <= 3; i++) {
				Thread.sleep(5000);
				executeProcess("adb -e emu finger touch 1");
			}
			WaitUntilElementIsDisplayed(By.xpath(doneText));
			driver.findElement(By.xpath("//android.widget.Button[@text='DONE']")).click();
			// Let's assume you are on fingerprint page
			// send the fingerprint
			((AndroidDriver) driver).fingerPrint(1);
			System.out.println("Finger print is added");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(driver.getPageSource());

		} finally {

			driver.quit();
		}
	}

	protected boolean WaitUntilElementIsDisplayed(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception ex) {
			System.out.println("Exception while waiting {ex.Message}" + ex.getMessage());
			return false;
		}
	}
	 public static void swipeUpWithoutTouchAction(WebDriver driver)throws Exception {
	        Dimension size = driver.manage().window().getSize();
	        int startX = size.width / 2;
	        int startY = (int) (size.height * 0.8);
	        int endY = (int) (size.height * 0.2);

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("mobile: dragFromToForDuration", 
	                         "{duration: 0.5, element: null, fromX: " + startX + ", fromY: " + startY + ", toX: " + startX + ", toY: " + endY + "}");
	    }

	private void executeProcess(String cmd) {
		ProcessBuilder processBuilder = new ProcessBuilder();

		// Run a shell command
		processBuilder.command("bash", "-c", cmd);

		// Run a shell script
		// processBuilder.command("xyz.sh");

		// if running on windows

		// Run a command
		// processBuilder.command("cmd.exe", "/c", cmd);

		try {

			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "");
			}

			BufferedReader ereader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			String eline;
			while ((eline = ereader.readLine()) != null) {
				output.append(eline + "");
			}

			int exitCode = process.waitFor();
			if (exitCode == 0) {
				System.out.println("Success");
				System.out.println(output);

			} else {
				System.out.println("Failure");
				System.out.println(output);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}