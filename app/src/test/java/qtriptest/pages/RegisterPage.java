package qtriptest.pages;

import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        PageFactory.initElements(driver, this);

    }

    public void navigateToRegisterPage(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url);
    }
   } 
 
   
    public boolean RegisterNewUser (String name,String password,Boolean generateRandomUsername) throws InterruptedException{

        
            if (generateRandomUsername){
            name = UUID.randomUUID().toString()+"@gmail.com";
        }

            lastgeneratedUserName= name;
            usernametxt.clear();
            usernametxt.sendKeys(name);
            passwordtxt1.clear();
            passwordtxt1.sendKeys(password);
            passwordtxt2.clear();
            passwordtxt2.sendKeys(password);
            registerbtn.click();

            Thread.sleep(3000);
            return generateRandomUsername;
    }


            // if(driver.getCurrentUrl().endsWith("/login"))
            //     driver.close();  

            public void checkIfUserIsOnLoginPage() {
                if (driver.getCurrentUrl().endsWith("/login")) {
                    driver.close();
            
                }
}

// RemoteWebDriver driver;
//     String Url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";

//     public String lastgeneratedUsername = "";
    
//     @FindBy (name="email")
//     WebElement usernameTextBx;

//     @FindBy (name="password")
//     WebElement passwordTextBx;

//     @FindBy (name="confirmpassword")
//     WebElement confirmPasswordBx;

//     @FindBy (xpath="//button[contains(text(),'Register Now')]")
//     WebElement RegisterNowButton;

//     public RegisterPage(RemoteWebDriver driver){
//         this.driver = driver;
//         PageFactory.initElements(driver, this);

//     }
 


//     public void navigateToRegistrationPage(){
//         if(!driver.getCurrentUrl().equals(this.Url)){
//             driver.get(this.Url);
//         }

//     }

    

//     public void RegisterNewUser(String Username, String password, String confirmPassword, Boolean generateRandomUsername) throws InterruptedException{

//         // if(generateRandomUsername==true){
//         //     Username = Username+UUID.randomUUID().toString();
//         if(generateRandomUsername){ 
//             Username = UUID.randomUUID().toString()+"@gmail.com";
// }
//             lastgeneratedUsername= Username;
//             usernameTextBx.clear();
//             usernameTextBx.sendKeys(Username);
//             passwordTextBx.clear();
//             passwordTextBx.sendKeys(password);
//             confirmPasswordBx.clear();
//             confirmPasswordBx.sendKeys(confirmPassword);
//             RegisterNowButton.click();
//             Thread.sleep(5000);

           
//         }

      

    
       
//     public void checkIfUserIsOnLoginPage() {
//     if (driver.getCurrentUrl().endsWith("/login")) {
//         driver.close();
//     }

// }
}

