package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {

    // RemoteWebDriver driver;
    // //String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/";

    // public AdventurePage(RemoteWebDriver driver){
    //     this.driver= driver;
    //     PageFactory.initElements(driver, this);
    //     // driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    //     // PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    // }


    // @FindBy (id= "duration-select")
    // WebElement select_hours;
 
    // @FindBy (xpath ="//select[@name='duration']//following-sibling::div")
    // WebElement clear_hours;

    // @FindBy (id ="category-select")
    // WebElement select_category;

    // @FindBy (xpath ="//select[@id='category-select']//following-sibling::div")
    // WebElement clear_Category;

    // @FindBy (id = "search-adventures")
    // WebElement Search_Adventure;

    // @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    // WebElement select_adventure;

    // @FindBy (xpath ="//*[@id='data']/div")
    // List<WebElement> adventuresCount;


    

    // // public void navigatetoAdventurePage(){
    // //     System.out.println(this.driver.getPageSource());
    // //     if(!this.driver.getCurrentUrl().equals(this.url)){
    // //         this.driver.get(this.url);
    // //     }
    // // }

    //     public void SetFilterValue(String DurationFilter){
    //         //clear_hours.click();
    //         Select select = new Select (select_hours);
    //         select.selectByVisibleText(DurationFilter);
    //         System.out.println(adventuresCount.size());
            
    //         // Search_Adventure.sendKeys(adventure);

    //     }

    //     public void SetCategoryValue(String Category_Filter){
    //         //clear_Category.click();

    //         Select select2 = new Select (select_category);
    //         select2.selectByVisibleText(Category_Filter);
    //         System.out.println(adventuresCount.size());

    //     }

    //     public void  clearFilter() throws InterruptedException, MalformedURLException
    //     {
      
    //       Thread.sleep(1000);
      
    //       clear_hours.click();
          
    //       clear_Category.click();
    //     }
        
    //     public boolean getResultCount(String filteredResult){
   
    //         Integer actualResult = adventuresCount.size();
    //     String result = actualResult.toString();
    //     if(result.equals(filteredResult)){
    //         return true;
    //     }
    //     return false;
    
    //     }

    //     public void searchAdventure(String AdventureName) throws InterruptedException{

    //         Thread.sleep(3000);
    //         Search_Adventure.sendKeys(AdventureName);
            
    //     }

    //     public void SelectAdventure() throws InterruptedException{
    //         Thread.sleep(3000);
    //         select_adventure.click();
            
    //     }

    RemoteWebDriver driver;

    @FindBy(id = "duration-select")
    WebElement select_hours;

    @FindBy(xpath = "//div[contains(text(),'Clear')][1]")
    WebElement clear_hours;

    @FindBy(id = "category-select")
    WebElement Select_category;

    @FindBy(xpath = "(//div[contains(text(),'Clear')])[2]")
    WebElement clear_Category;

    @FindBy(id = "search-adventures")
    WebElement search_adventure;

    @FindBy(xpath = "(//div[contains(text(),'Clear')])[3]")
    WebElement clear_Adventure;

    @FindBy(xpath = "//*[@id='data']/div[1]")
    WebElement select_Adventure;

    @FindBy(xpath = "//input[@id='search-adventures']")
    List<WebElement> numberOfAdventure;

    String Url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city=paris";

    public AdventurePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void navigatetoAdventurePage() {
        // if (!driver.getCurrentUrl().equals(this.Url)) {
        //     driver.get(this.Url);
        // }
        SeleniumWrapper.navigate(driver, Url);
    }

    public void SetFilterValue(String DurationFilter) {
        Select select1 = new Select(select_hours);
        // select1.selectByIndex(3);
        select1.selectByVisibleText(DurationFilter);
    //         System.out.println(adventuresCount.size());
    }

    public boolean getResultCount(String filteredResult) throws MalformedURLException {
        //clear_hours.click();
        SeleniumWrapper.click(clear_hours);
        //clear_Category.click();
        SeleniumWrapper.click(clear_Category);
        Integer actualResult = numberOfAdventure.size();
        String result = actualResult.toString();
        return result.equals(filteredResult);
    }

    public void setCategoryValue(String Category_Filter) {
        Select select2 = new Select(Select_category);
        // select2.selectByIndex(3);
        select2.selectByVisibleText(Category_Filter);
        //System.out.println(adventuresCount.size());
    }

    public void ClearFilterValue() throws MalformedURLException {
        //clear_Adventure.click();
        SeleniumWrapper.click(clear_Adventure);
    }

    public void searchAdventure(String AdventureName) throws InterruptedException {

        Thread.sleep(3000);
        //search_adventure.sendKeys(AdventureName);
        SeleniumWrapper.sendKeys(search_adventure, AdventureName);

    }

    public void SelectAdventure() throws MalformedURLException {

        //select_Adventure.click();
        SeleniumWrapper.click(select_Adventure);
    }
}
       
