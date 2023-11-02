package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_01 {

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
        @Test(enabled = true, dataProvider = "DatasetsforQTrip", dataProviderClass = DP.class,description = "verify Login flow" , priority = 1, groups={"Login Flow"})
        public static void TestCase01 (String username, String password) throws InterruptedException{


			driver.manage().window().maximize();
		    Thread.sleep(5000);

			HomePage home = new HomePage(driver);
			home.navigatetoHomePage();

            RegisterPage register= new RegisterPage(driver);
            register.navigateToRegisterPage();
			//register.RegisterNewUser(username,password, true);
			Assert.assertTrue(register.RegisterNewUser(username, password,true));
			lastGeneratedUsername = register.lastgeneratedUserName;

			LoginPage login = new LoginPage(driver);
			login.navigateToLoginPage();
			login.PerformLogin(lastGeneratedUsername, password);

           // Assert.assertTrue(login.VerifyUserLoggedIn());
		   Thread.sleep(3000);
		   login.logout();
		//Assert.assertTrue(login.VerifyUserLoggedIn());

	}

	@AfterSuite
	public static void quitDriver() {
	System.out.println("quit()");
	driver.quit();
    }

}
