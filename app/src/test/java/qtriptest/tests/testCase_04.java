package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.util.ArrayList;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_04 {

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
                //ReportSingleton.report= ReportSingleton.getReportInstance();
        
            }

        @Test(dataProvider = "DatasetsforQTrip", dataProviderClass =DP.class, enabled = true,description = "verify Relaibility flow" , priority = 4, groups={"Reliability Flow"})
        public static void TestCase04(String NewUserName,String Password, String dataset1,String dataset2,String dataset3) throws InterruptedException, MalformedURLException{

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
                String [] data1 =  dataset1.split(";");
                String [] data2 =  dataset2.split(";");
                String [] data3 =  dataset3.split(";");

                ArrayList<String[]> list = new ArrayList<>();

                list.add(data1);
                list.add(data2);
                list.add(data3);

                ReportSingleton.test=ReportSingleton.report.startTest( "Verify booking history can be viewed");

                for(String[] data : list){


                        Thread.sleep(4000);
                        home.searchCity(data[0]);
                    
                        Thread.sleep(4000);
                        home.SelectCity();

                        AdventurePage adventurePage = new AdventurePage(driver);
                        Thread.sleep(2000);
                        adventurePage.searchAdventure(data[1]);
                        Thread.sleep(2000);
                        adventurePage.SelectAdventure();

                        AdventureDetailsPage adventureDetails= new AdventureDetailsPage(driver);
                        adventureDetails.BookAdventure(data[2], data[3], data[4]);
                        adventureDetails.isBookingSuccesful();

                        home.navigatetoHomePage();


                }

                HistoryPage history= new HistoryPage(driver);
                history.clickonReservation();
                Thread.sleep(3000);
                ReportSingleton.test.log(LogStatus.PASS, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+" Successfully  verified  booking history");
                history.GetReservations();
                ReportSingleton.test.log(LogStatus.FAIL, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+" Failed to verify booking history");
                Thread.sleep(3000);
                home.LogoutUser();

        }

        @AfterSuite
        public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
        }


}
