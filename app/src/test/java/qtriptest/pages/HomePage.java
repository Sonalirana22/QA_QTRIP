package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath= "//div[contains(text(),'Logout')]")
    WebElement LogOutBtn;

    @FindBy(xpath ="//a[contains(text(),'Register')]")
    WebElement RegisterBtn;

    @FindBy (xpath= "//input[contains(@placeholder,'Search a City')]")
    WebElement SearchBtn;

    @FindBy (xpath = "//h5[contains(text(),'No City found')]")
    WebElement CityNotFound;


    public HomePage(RemoteWebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public void navigatetoHomePage(){
        System.out.println(this.driver.getPageSource());
        if(!this.driver.getCurrentUrl().equals(this.url)){
            this.driver.get(this.url);
        }
    }

    public void ClickRegister(){
        RegisterBtn.click();

    }

    public boolean IsUserLoggedin(){
        try{
            if(LogOutBtn.isDisplayed()){
                return true;
            }else{
                return false;
            }
    }catch(Exception e){
        System.out.println("Element Not Found");
        return false;
    }

    }

    public void LogoutUser(){
        LogOutBtn.click();
        
    }



    // RemoteWebDriver driver;
    // String Url = "https://qtripdynamic-qa-frontend.vercel.app/";

    // public HomePage(RemoteWebDriver driver){
    //     this.driver= driver;
    //     PageFactory.initElements(this.driver, driver);
    // }

    // public void navigatetoHomePage(){
    //     System.out.println(driver.getPageSource());
    //     if(!driver.getCurrentUrl().equals(this.Url)){
    //         driver.get(this.Url);
    //     }
        
    // }

    // @FindBy(xpath= "//div[contains(text(),'Logout')]")
    // WebElement LogOutBtn;

    // @FindBy(xpath ="//a[contains(text(),'Register')]")
    // WebElement RegisteBtn;

    // @FindBy (xpath= "//input[contains(@placeholder,'Search a City')]")
    // WebElement SeachBtn;

    // @FindBy (xpath = "//h5[contains(text(),'No City found')]")
    // WebElement CityNotFound;


    // public void ClickOnRegister(){
    //     RegisteBtn.click();
    // }

    // public boolean isUserLoggedIn(){
    //     try{
    //             if(LogOutBtn.isDisplayed()){
    //                 return true;
    //             }else{
    //                 return false;
    //             }
    //     }catch(Exception e){
    //         System.out.println("Element Not Found");
    //         return false;
    //     }

       
    // }
}
