package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {

    RemoteWebDriver driver;
    String url ="https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastgeneratedUserName ="";

    @FindBy (name = "email")
   WebElement usernametxt;

   @FindBy (xpath = "(//input[@id='floatingPassword'])[1]")
   WebElement passwordtxt1;

   @FindBy (xpath = "(//input[@id='floatingPassword'])[2]")
   WebElement passwordtxt2;

   @FindBy (xpath = "//button[contains(text(),'Register Now')]")
   WebElement registerbtn;

    public  RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        // PageFactory.initElements(driver, this);
        AjaxElementLocatorFactory ajax= new AjaxElementLocatorFactory(driver,  10);
        PageFactory.initElements(ajax, this);

    }

    public void navigateToRegisterPage(){
        SeleniumWrapper.navigate(driver, url);
        // if(!driver.getCurrentUrl().equals(this.url)){
        //     driver.get(this.url);
    }
   
 
   
    public boolean RegisterNewUser (String name,String password,Boolean generateRandomUsername) throws InterruptedException{

        
            if (generateRandomUsername){
            name = UUID.randomUUID().toString()+"@gmail.com";
        }

            lastgeneratedUserName= name;
            usernametxt.clear();
            // usernametxt.sendKeys(name);
            SeleniumWrapper.sendKeys(usernametxt, name);
            passwordtxt1.clear();
            //passwordtxt1.sendKeys(password);
            SeleniumWrapper.sendKeys(passwordtxt1, password);
            passwordtxt2.clear();
            //passwordtxt2.sendKeys(password);
            SeleniumWrapper.sendKeys(passwordtxt2, password);
            registerbtn.click();

            Thread.sleep(3000);
            return generateRandomUsername;
    } 

    public void checkIfUserIsOnLoginPage() {
    if (driver.getCurrentUrl().endsWith("/login")) {
    driver.close();
            }
    }

}



