package testMfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.*;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import net.bytebuddy.implementation.bytecode.Duplication;

public class LockAuthentication {
	// Tested By Ramanujam and Swapna

	@Test
	public void test1() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("src\\test\\java\\testMfa\\config.properties")));
		//Desired capabilities
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", prop.getProperty("platformName"));
		dc.setCapability("deviceName", prop.getProperty("deviceName"));
		//dc.setCapability("appPackage", prop.getProperty("appPackage"));
		//dc.setCapability("appActivity", prop.getProperty("appActivity"));
		dc.setCapability("automationName", prop.getProperty("automationName"));
		
        
		URL url = URI.create(prop.getProperty("appiumUrl")).toURL();
		
		AndroidDriver driver = new AndroidDriver(url,dc);
		System.out.println("application Started");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//executeProcess("adb -e emu finger touch "+prop.getProperty("fingerPrint"));
		//Assert.assertEquals("Fingerprint not recognized", "Fingerprint not recognized");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//System.out.println("Fingerprint not recognized with Invalid Fingerprint"+prop.getProperty("invalidFingerPrint"));
		executeProcess("adb -e emu finger touch "+prop.getProperty("fingerPrint"));
		Thread.sleep(500);
		try {
			Boolean text = driver.findElement(By.id("com.google.android.apps.nexuslauncher:id/overview_actions_view")).isDisplayed();
			System.out.println("Text is "+text);
			Assert.assertTrue(text);
			System.out.println("Fingerprint recognized with valid Fingerprint "+prop.getProperty("fingerPrint"));
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fingerprint not recognized with Invalid Fingerprint "+prop.getProperty("fingerPrint"));
			Assert.assertTrue(false);
		}
		Thread.sleep(500);
		driver.quit();
	}
		private void executeProcess(String cmd) {
			ProcessBuilder processBuilder = new ProcessBuilder();

			// Run a shell command
			//processBuilder.command("bash", "-c", cmd);

			// Run a shell script
			// processBuilder.command("xyz.sh");

			// if running on windows

			// Run a command
			 processBuilder.command("cmd.exe", "/c", cmd);

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
