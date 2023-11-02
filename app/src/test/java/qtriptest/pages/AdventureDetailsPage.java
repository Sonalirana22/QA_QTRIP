package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdventureDetailsPage {
    RemoteWebDriver driver;

    public AdventureDetailsPage(RemoteWebDriver driver){
        this.driver = driver ;
        PageFactory.initElements(this.driver,this);

    }

@FindBy(xpath ="//*[@id='myForm']/input[1]")
WebElement name_box;

@FindBy (xpath ="//*[@id='myForm']/input[2]")
WebElement date_box;

@FindBy (xpath = "//*[@id='myForm']/div[1]/div[2]/input")
WebElement person_count;

@FindBy (xpath ="//*[@id='myForm']/button")
WebElement reserve_btn;

@FindBy (id ="reserved-banner")
WebElement greeting_text;

@FindBy (xpath="//*[@id='reserved-banner']/a/strong")
WebElement click_here_link;

    public void BookAdventure(String name, String date, String count) throws InterruptedException{
            Thread.sleep(2000);
            name_box.clear();
            name_box.sendKeys(name);
            date_box.sendKeys(date);
            person_count.clear();
            person_count.sendKeys(count);
            reserve_btn.click();


    }

    public boolean isBookingSuccesful() throws InterruptedException{

        Thread.sleep(2000);

        if(greeting_text.isDisplayed()){
            return  true;
        }else{
            return false;
        }
    }


        public void clickonHistoryPage(){
        
            click_here_link.click();

        }



}