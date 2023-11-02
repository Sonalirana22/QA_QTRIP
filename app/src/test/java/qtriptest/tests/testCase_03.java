package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class testCase_03 {

        static RemoteWebDriver driver;
        public static String lastGeneratedUsername;

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
    
        }

        @Test(dataProvider = "DatasetsforQTrip", dataProviderClass =DP.class, enabled = true,description = "verify booking and cancellation flow" , priority = 3, groups={"Booking and Cancellation Flow"})
        public static void TestCase03(String NewUserName,String Password, String SearchCity,String AdventureName,String GuestName,String Date,String count) throws InterruptedException{

                driver.manage().window().maximize();
                Thread.sleep(5000);

                HomePage home = new HomePage(driver);
                home.navigatetoHomePage();

                RegisterPage register= new RegisterPage(driver);
                register.navigateToRegisterPage();
                //register.RegisterNewUser(username,password, true);
                //Assert.assertTrue(register.RegisterNewUser(NewUserName, Password,true));
                register.RegisterNewUser(NewUserName, Password, true);
                String lastGeneratedUsername = register.lastgeneratedUserName;

                LoginPage login = new LoginPage(driver);
                //login.navigateToLoginPage();
                login.PerformLogin(lastGeneratedUsername, Password);
                //Assert.assertTrue(login.VerifyUserLoggedIn());

                home.searchCity(SearchCity);
            
                Thread.sleep(2000);
                //Assert.assertTrue(home.AssertAutoCompleteText());
                home.SelectCity();

                AdventurePage adventurePage = new AdventurePage(driver);
                Thread.sleep(1000);
                adventurePage.searchAdventure(AdventureName);
                Thread.sleep(1000);
                adventurePage.SelectAdventure();

                AdventureDetailsPage adventureDetails= new AdventureDetailsPage(driver);
                adventureDetails.BookAdventure(GuestName, Date, count);
                adventureDetails.isBookingSuccesful();
                adventureDetails.clickonHistoryPage();
                Thread.sleep(2000);
                HistoryPage history= new HistoryPage(driver);
                //history.navigateToHistoryPage();
                history.GetReservations();
                Thread.sleep(5000);
                history.CancelReservation();
                driver.navigate().refresh();
                history.verifyCancelReservation();

        }

        @AfterSuite
        public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
        }

}
