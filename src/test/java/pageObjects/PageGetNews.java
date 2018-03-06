package pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/* This webpage, I only included necessary elements which i did testing . 
 * for timestrain, I didn't include some text elements I 
 */

public class PageGetNews {
	
   protected WebDriver driver;
   
   By pageTitle = By.xpath("//h4[@class='modal-title']");
   By firstName = By.id("firstName");
   By lastName = By.id("lastName");
   By email = By.id("userEmail");
   By board = By.id("boardName");
   By role = By.id("educatorRoleId");
   By submit = By.id("getNewsSubmit");
   
   By firstNameError = By.xpath("//div[@class='alert alert-danger'][contains(text(),'first name')]");
   By lastNameError = By.xpath("//div[@class='alert alert-danger'][contains(text(),'last name')]");
   By emailError = By.xpath("//div[@class='alert alert-danger'][contains(text(),'email')]");
   By roleError = By.xpath("//div[@class='alert alert-danger'][contains(text(),'role')]");
   By success = By.xpath("//div[@class='alert alert-success']");
   
   By close = By.xpath("//a[@href='#'][contains(text(),'X')]");

    public PageGetNews(WebDriver driver)
    { 
    	this.driver = driver; 
    }
        
    public WebElement getPageTitle()
    { 
    	return driver.findElement(pageTitle);
    }
    
    public WebElement getFirstName()
    {
    	return driver.findElement(firstName);
    }
    
    public WebElement getLastName()
    {
    	return driver.findElement(lastName);
    }
    
    public WebElement getEmail()
    {
    	return driver.findElement(email);
    }
    
    public Select getBoard()
    {
    	return new Select(driver.findElement(board));
    }
    
    public Select getRole()
    {
    	return new Select(driver.findElement(role));
    }
    
    public WebElement getSubmitButton()
    {
    	return driver.findElement(submit);
    }
     
    
    public List<WebElement> getFirstNameError()
    {
    	return driver.findElements(firstNameError);
    }
    
    public List<WebElement> getLastNameError()
    {
    	return driver.findElements(lastNameError);
    }
    
    public List<WebElement> getEmailError()
    {
    	return driver.findElements(emailError);
    }
    
    public List<WebElement> getRoleError()
    {
    	return driver.findElements(roleError);
    }
    
    public List<WebElement> getSuccessMessage()
    {
    	return driver.findElements(success);
    }
    
    public WebElement getClose()
    {
    	return driver.findElement(close);
    }
}
