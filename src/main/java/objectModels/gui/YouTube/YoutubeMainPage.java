package objectModels.gui.YouTube;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;

public class YoutubeMainPage {
    public WebDriver driver;

    public String url = "https://www.youtube.com/";

    public YoutubeMainPage(WebDriver driver){
        this.driver = driver;
        BrowserActions.navigateToURL(driver, url);
     //   this.localDriver.navigate().to(url);
    }

    public YoutubeSearchPage doASearchQueryOnMainPage(String searchQuery){
    	ElementActions.isElementDisplayed(driver, By.xpath("//ytd-searchbox/form/div[1]/div[1]/input"));
    	WebElement searchBar = this.driver.findElement(By.xpath("//ytd-searchbox/form/div[1]/div[1]/input"));
        searchBar.sendKeys(searchQuery);
        searchBar.sendKeys(Keys.RETURN);
        return new YoutubeSearchPage(this.driver);
    }
}
