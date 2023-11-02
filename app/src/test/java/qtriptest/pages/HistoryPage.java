
package qtriptest.pages;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {
    RemoteWebDriver driver;
    String url="https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/";

    public HistoryPage(RemoteWebDriver driver){

        this.driver= driver;
        PageFactory.initElements(this.driver, driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
        // AjaxElementLocatorFactory ajax= new AjaxElementLocatorFactory(driver,20);
        // PageFactory.initElements(ajax, this);
    }

    public void navigateToHistoryPage() throws InterruptedException{
        if(!driver.getCurrentUrl().equals(url)){
          driver.get(url);
      }
    }


    @FindBy(xpath = "//a[contains(text(),'Reservations')]")
   WebElement Reservation ;

    @FindBy(xpath="//*[@id='reservation-table']/tr/th")
    WebElement transaction_id;

    @FindBy(xpath = "//td//preceding-sibling::th")
    List<WebElement> transactionId;

    @FindBy(xpath= "//button[@class='cancel-button']")
    WebElement cancel_btn;

    @FindBy(xpath="//*[@id='no-reservation-banner']")
    WebElement no_reservation_banner;

    @FindBy (xpath ="//a[contains(text(),'Visit Adventure')]")
    
     List<WebElement> numberOfReservations;



     public void GetReservations() throws InterruptedException{
        try {
                        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
                        Thread.sleep(1000);
                        for (int i = 0; i < transactionId.size(); i++) {
                            System.out.println("Transaction id: " + transactionId.get(i).getText());
                        }
            
                    } catch (Exception e) {
                        System.out.println(e);
                    }
    
            
    }

    public void CancelReservation() throws InterruptedException{
        try {
            System.out.println("Here driver is :- "+driver);
            System.out.println("current URL is:- " + driver.getCurrentUrl());
            WebElement btn = driver.findElement(By.xpath("//button[@class='cancel-button']"));
            System.out.println("Here button is :- "+btn.getText());
            btn.click();
            Thread.sleep(5000);
            //driver.navigate().refresh();
            //  if(!transaction_id.isDisplayed()){
            //     return true;
            //  }else
            //     return false;
        } catch(Exception e) {
            e.printStackTrace();
        }
        }


        public boolean verifyCancelReservation() {
            WebElement box = driver.findElement(By.xpath("//*[@id='no-reservation-banner']"));
            if (box.getText().contains("Oops! You have not made any reservations yet!")) {
                return true;
            } else {
                return false;
            }
        }


        public void clickonReservation() throws InterruptedException{
            Thread.sleep(2000);
            System.out.println("---------->" +Reservation);
            Reservation.click() ;
            Thread.sleep(2000);
        // SeleniumWrapper.click(HomeBtn) ;
    }



    
}