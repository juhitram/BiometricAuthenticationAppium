package pageObjects;

import org.openqa.selenium.WebDriver;

public class BasePage {
	protected WebDriver drive;
    public BasePage(WebDriver drive){
    this.drive = drive;
    }

}
