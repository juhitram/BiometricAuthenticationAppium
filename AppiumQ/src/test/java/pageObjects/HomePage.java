package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
	public HomePage(WebDriver drive)
    {
        super(drive);
        PageFactory.initElements(drive,this);
    }

    @FindBy (xpath = "//android.widget.TextView[@resource-id='com.android.settings:id/search_action_bar_title']")
    WebElement settingsSearch;
    
    @FindBy (xpath = "//android.widget.EditText[@resource-id='com.google.android.settings.intelligence:id/open_search_view_edit_text']")
    WebElement SearchEdit;
    
    public void clickSettings(){
        settingsSearch.click();
    }

}
