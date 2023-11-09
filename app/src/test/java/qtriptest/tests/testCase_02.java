package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_02 {

    static RemoteWebDriver driver;
    //public static String lastGeneratedUsername;

 	// Method to help us log our Unit Tests
     public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeSuite(alwaysRun = true, enabled = true)
	public static void createDriver() throws MalformedURLException {
      logStatus("driver", "Initializing driver", "Started");
      // final DesiredCapabilities capabilities = new DesiredCapabilities();
      // capabilities.setBrowserName(BrowserType.CHROME);
      // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
      driver=DriverSingleton.getDriverInstance("chrome");
      logStatus("driver", "Initializing driver", "Success");
      //ReportSingleton.report= ReportSingleton.getReportInstance();
   
   }

      @Test(enabled = true, dataProvider = "DatasetsforQTrip", dataProviderClass = DP.class,description = "verify Search and Filter flow" , priority = 2, groups={"Search and Filter flow"})
      public static void TestCase02(String CityName, String CategoryFilter, String DurationFilter,String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws InterruptedException, MalformedURLException{
        
        ReportSingleton.test=ReportSingleton.report.startTest( "Verify that search and filter Work fine ");
        String CityNotPresent = "Delhi"  ;
        driver.manage().window().maximize();
        Thread.sleep(5000);


        HomePage home = new HomePage(driver);
        home.navigatetoHomePage();

        home.searchCity(CityNotPresent);
        //assertTrue(home.cityNotFound(), "No city found is not displayed ");
        home.cityNotFound();

        home.searchCity(CityName);
        
        //assertTrue(home.AssertAutoCompleteText(), "City suggestion is not displayed");
        home.AssertAutoCompleteText();
        
        home.SelectCity();

        AdventurePage adventurepage = new AdventurePage(driver);
        //adventurepage.navigatetoAdventurePage();
        //adventurepage.clearFilter();
        Thread.sleep(1000);

        int expectedUnfilterCount = Integer.parseInt(ExpectedUnFilteredResults);

        //Assert.assertEquals(adventurepage.getResultCount(), expectedUnfilterCount);

        adventurepage.SetFilterValue(DurationFilter);
        adventurepage.setCategoryValue(CategoryFilter);
        if(adventurepage.getResultCount(ExpectedFilteredResults)){
        ReportSingleton.test.log(LogStatus.PASS,ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+ "Succesfully  Verify Search and Filter flow ");
        }else{
          ReportSingleton.test.log(LogStatus.FAIL,ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+ "Failed to Verify Search and Filter flow ");
        }
    }


    @AfterSuite
	public static void quitDriver() {
	System.out.println("quit()");
	driver.quit();
    }
}
