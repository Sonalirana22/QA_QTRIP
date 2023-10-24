package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    RemoteWebDriver driver;
    String Url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    public LoginPage(RemoteWebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);

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

public void logout(){
    logoutbutton.click();
}


// RemoteWebDriver driver;
//     String Url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";
//     public String lastgeneratedUsername = "";

//     public LoginPage(RemoteWebDriver driver){
//         this.driver=driver;
//         PageFactory.initElements(this.driver,this);
// }
//         public void navigatetoLoginPage(){
//             if(!driver.getCurrentUrl().equals(Url)){
//                 driver.get(Url);
//             }
//         }

//         @FindBy (name ="email") 
//         WebElement email_Text_box;
        
//         @FindBy (name= "password")
//         WebElement password_Text_box;

//         @FindBy (xpath= "//button[text()='Login to QTrip']")
//         WebElement login_toqtrip_Button_box;
        
//         @FindBy(xpath = "//div[text()='Logout']")
//         WebElement log_out_button;
        
//         @FindBy (xpath = "//a[text()='Login Here']")
//         WebElement Login_here_verify;
       



//         public void performLogin(String username, String password){
//             try{
//                 email_Text_box.sendKeys(username);
//                 password_Text_box.sendKeys(password);
//                 login_toqtrip_Button_box.click();
//                 Thread.sleep(5000);

//             }catch(Exception e){
//                 System.out.println(e);
//             }


//         }

//         public boolean verifyLogin(){
//             if(log_out_button.isDisplayed()){
//                 return true;
//             }else{
//                 return false;
//             }
//         }

        
//         public boolean verifyLogOut(){
//             if(Login_here_verify.isDisplayed()){
//                 return true;
//             }else{
//                 return false;
//             }
//         }


//         public void logout(){
//             log_out_button.click();
//         }

}
