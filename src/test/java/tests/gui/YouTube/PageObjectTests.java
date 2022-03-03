package tests.gui.YouTube;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import com.shaft.tools.io.ExcelFileManager;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import objectModels.gui.YouTube.YoutubeMainPage;
import objectModels.gui.YouTube.YoutubeSearchPage;
import objectModels.gui.YouTube.YoutubeVideo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class PageObjectTests {

    public static WebDriver driver;

   // @SuppressWarnings("deprecation")
	@BeforeMethod
    public void setUp(){
    //	WebDriverManager.chromedriver().setup();
    	//driver = new ChromeDriver();
		//simpleSearch = new ExcelFileManager(System.getProperty("testDataFolderPath") + "Youtube/youtubeSearchQueryData.xlsx");

        driver = BrowserFactory.getBrowser();

    }
   // private ExcelFileManager simpleSearch;

    public static Sheet readExcel(String filePath, String fileName, String sheetName) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        return workbook.getSheet(sheetName);
    }
    
    public static ArrayList<Object[]> getDataFromExcel() throws IOException {
        ArrayList<Object[]> list = new ArrayList<>();
        Sheet sheet = readExcel(System.getProperty("testDataFolderPath") + "Youtube", "youtubeSearchQueryData.xlsx", "testYoutubeSearchQuery");
        int lastRowCount = sheet.getLastRowNum() + 1;
        for (int i = 1; i < lastRowCount; i++) {
            String run = sheet.getRow(i).getCell(0).getStringCellValue();
            String query = sheet.getRow(i).getCell(1).getStringCellValue();
            String Videonum = String.valueOf( sheet.getRow(i).getCell(2).getNumericCellValue());

            Object[] ob = {run, query,Videonum};
            if (run.equals("do")) {
                list.add(ob);
            }
        }
        return list;
    }

    @DataProvider
    public static Iterator<Object[]> youtubeSearchQueryProvider() throws IOException {
        ArrayList<Object[]> list = getDataFromExcel();
        return list.iterator();
    }
    
    @Test(dataProvider = "youtubeSearchQueryProvider")
    public void OpenYoutubeMainPage(String run, String expectedQuery,String Videonum ) throws InterruptedException{
        
    	
    	YoutubeMainPage youtubeMainPage = new YoutubeMainPage(driver);
      
        //GoToSearchResults
       // YoutubeMainPage youtubeMainPage = new YoutubeMainPage(driver);
        YoutubeSearchPage youtubeSearchPage = youtubeMainPage.doASearchQueryOnMainPage(expectedQuery);
        youtubeSearchPage.ClickOnFilter();
        youtubeSearchPage.ClickOnType();
        
        //GoToFirstResultOfSearch

        YoutubeVideo youtubeVideo = youtubeSearchPage.openNrowSearchResult(Videonum);

    }


    @AfterMethod
    public void afterMethod() {
        BrowserActions.closeCurrentWindow(driver);
    }
}
