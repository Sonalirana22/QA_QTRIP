package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

    RemoteWebDriver driver;
    String Url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    public LoginPage(RemoteWebDriver driver){
        this.driver= driver;
        //PageFactory.initElements(driver, this);
        AjaxElementLocatorFactory ajax= new AjaxElementLocatorFactory(driver,  10);
        PageFactory.initElements(ajax, this);

    }

    public void navigateToLoginPage(){
        if(!driver.getCurrentUrl().equals(Url)){
            driver.get(Url);
        }
    }

        @FindBy (name ="email") 
        WebElement emailtxtbox;
        
        @FindBy (name= "password")
        WebElement passwordtxtbox;

        @FindBy (xpath= "//button[text()='Login to QTrip']")
        WebElement logintoqtripbutton;
        
        @FindBy(xpath = "//div[text()='Logout']")
        WebElement logoutbutton;
        
        @FindBy (xpath = "//a[text()='Login Here']")
        WebElement Login_here_verify;

    public void PerformLogin(String username, String password) throws InterruptedException
{
     emailtxtbox.sendKeys(username);
     Thread.sleep(1000);
     passwordtxtbox.sendKeys(password);
     Thread.sleep(1000);
     logintoqtripbutton.click();

}

public Boolean VerifyUserLoggedIn(){
    if(logoutbutton.isDisplayed()){
        return true;
    }else{
        return false;
    }
}

public void logout() throws InterruptedException{
    Thread.sleep(2000);
    logoutbutton.click();
}

}
