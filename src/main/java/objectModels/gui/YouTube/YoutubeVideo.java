package objectModels.gui.YouTube;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import objectModels.gui.YouTube.HelperFunctions;

public class YoutubeVideo {

    public WebDriver driver;

    public YoutubeVideo(WebDriver driver){
        this.driver = driver;
    }

    public void stopVideoPlaybackAfter10Seconds() throws InterruptedException {
        WebElement video = this.driver.findElement(By.cssSelector("#movie_player"));
        Thread.sleep(10000);
        video.click();
    }

    public void goToCommentsSection(){
        WebElement commentSection = this.driver.findElement(By.id("sections"));
        HelperFunctions.MoveToElementUsingActions(this.driver, commentSection);
    }

    public void goToTheBottomOfThePage(){
        HelperFunctions.ScrollToTheBottomOfThePage(this.driver);
    }
}
