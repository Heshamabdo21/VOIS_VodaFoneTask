package objectModels.gui.YouTube;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Assertions;
import com.shaft.validation.Assertions.AssertionComparisonType;
import com.shaft.validation.Assertions.AssertionType;
import com.shaft.validation.Verifications;


public class YoutubeSearchPage {
    public WebDriver driver;

    public YoutubeSearchPage(WebDriver driver){
        this.driver = driver;
    }

    public YoutubeVideo ClickOnFilter(){
    	ElementActions.isElementDisplayed(driver, By.xpath("//ytd-toggle-button-renderer/a/tp-yt-paper-button/yt-formatted-string"));

        WebElement Filter = this.driver.findElement(By.xpath("//ytd-toggle-button-renderer/a/tp-yt-paper-button/yt-formatted-string"));
        Filter.click();
        return new YoutubeVideo(this.driver);
    }
    
    
    public YoutubeVideo ClickOnType(){
    	ElementActions.isElementDisplayed(driver, By.xpath("//iron-collapse/div/ytd-search-filter-group-renderer[2]/ytd-search-filter-renderer[1]/a/div/yt-formatted-string"));

        WebElement Filter = this.driver.findElement(By.xpath("//iron-collapse/div/ytd-search-filter-group-renderer[2]/ytd-search-filter-renderer[1]/a/div/yt-formatted-string"));
        Filter.click();
        return new YoutubeVideo(this.driver);
    }
    
    public YoutubeVideo openNrowSearchResult(String Videonum) throws InterruptedException{
    	Actions actions = new Actions(driver); 

    	// Call moveToElement() method of actions class to move mouse cursor to a WebElement A. 
    	   actions.moveToElement(driver.findElement(By.xpath("//ytd-video-renderer["+Videonum+"]/div[1]/div/div[1]/div/h3/a/yt-formatted-string"))); 
    	   actions.release().perform(); 
    	   
    	ElementActions.isElementDisplayed(driver, By.xpath("//ytd-video-renderer["+Videonum+"]/div[1]/div/div[1]/div/h3/a/yt-formatted-string"));
        WebElement NrowResult = this.driver.findElement(By.xpath("//ytd-video-renderer["+Videonum+"]/div[1]/div/div[1]/div/h3/a/yt-formatted-string"));
        String ExpectedVideoName = NrowResult.getText();
        //Thread.sleep(10000);
        NrowResult.click();
    	ElementActions.isElementDisplayed(driver, By.xpath("//div[5]/div[1]/div/div[6]/div[2]/ytd-video-primary-info-renderer/div/h1/yt-formatted-string"));
       // Thread.sleep(10000);

       // WebElement ActualOpenedVideo = this.driver.findElement(By.xpath("//div[5]/div[1]/div/div[6]/div[2]/ytd-video-primary-info-renderer/div/h1/yt-formatted-string"));
      //  String ActualOpenedVideoName = ActualOpenedVideo.getText();
       // Assert.assertEquals(ActualOpenedVideoName, ExpectedVideoName,"");
        //Thread.sleep(10000);

        //Verifications.verifyEquals(ExpectedVideoName,ActualOpenedVideoName, Verifications.VerificationComparisonType.MATCHES, Verifications.VerificationType.POSITIVE);
        
        Assertions.assertElementAttribute(driver, By.xpath("//div[5]/div[1]/div/div[6]/div[2]/ytd-video-primary-info-renderer/div/h1/yt-formatted-string"), "Text",
        		ExpectedVideoName, AssertionComparisonType.CONTAINS,
        		AssertionType.POSITIVE, "Asserting that the correct result is displayed");
        return new YoutubeVideo(this.driver);
    }
    

    
    
    public YoutubeVideo openFirstSearchResult(){
    	ElementActions.isElementDisplayed(driver, By.xpath("//ytd-video-renderer[1]/div[1]/div/div[1]/div/h3/a/yt-formatted-string"));

        WebElement firstResult = this.driver.findElement(By.xpath("//ytd-video-renderer[1]/div[1]/div/div[1]/div/h3/a/yt-formatted-string"));
        firstResult.click();
        return new YoutubeVideo(this.driver);
    }
}
