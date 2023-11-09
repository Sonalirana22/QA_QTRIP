package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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

    @FindBy (id = "results")
    WebElement CityFound;


    public HomePage(RemoteWebDriver driver){
        this.driver= driver;
        //PageFactory.initElements(driver, this);
        // driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        // PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
        AjaxElementLocatorFactory ajax= new AjaxElementLocatorFactory(driver,20);
        PageFactory.initElements(ajax, this);
    }

    public void navigatetoHomePage(){
        // System.out.println(this.driver.getPageSource());
        // if(!this.driver.getCurrentUrl().equals(this.url)){
        //     this.driver.get(this.url);
        // }
        SeleniumWrapper.navigate(driver, url);
    }

    public void ClickRegister() throws MalformedURLException{
        //RegisterBtn.click();
        SeleniumWrapper.click(RegisterBtn);

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

    public void searchCity(String city) throws InterruptedException, MalformedURLException{

        SearchBtn.clear();
        Thread.sleep(2000); //Crio Team added
       // SearchBtn.click();
       SeleniumWrapper.click(SearchBtn);
        Thread.sleep(2000); //Crio Team added
        //SearchBtn.sendKeys(city);
        SeleniumWrapper.sendKeys(SearchBtn, city);

    }
     
    public boolean cityNotFound() throws InterruptedException{
        try {
            Thread.sleep(1000);
            
        if(CityNotFound.isDisplayed())
            return true;
        } catch ( Exception e) {
            
            return false;

        }
        return false;

    }

    public Boolean AssertAutoCompleteText(){
        if(CityFound.isDisplayed()){
            return true;
        }else
         return false;

    }

    public boolean SelectCity() throws InterruptedException, MalformedURLException{  
        if(CityFound.isDisplayed()){
            Thread.sleep(3000);
            //CityFound.click();
            SeleniumWrapper.click(CityFound);
           }else{
            return false;
           }
        return true;
           

    }

    public void LogoutUser() throws MalformedURLException{
        //LogOutBtn.click();
        SeleniumWrapper.click(LogOutBtn);
        
    }

}
