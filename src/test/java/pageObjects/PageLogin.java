package pageObjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/* Since I only need test getNew Page ,to make test cases simple,  
 *  this login page, I didn't include all elements, and actually  I only use 'getNew' button in this page
*/ 
public class PageLogin {

    protected WebDriver driver;
    By userName = By.id("username");
    By passWord = By.id("password");
    By login = By.id("educatorLoginButton");
    By forGetPwd = By.xpath("//a[@href='#/lostpassword']");
  //  By emailError = By.xpath("//div[@class='alert alert-danger'][contains(text(),'valid email')]");
 //   By passwordError = By.xpath("//div[@class='alert alert-danger'][contains(text(),'password')]");
    By register = By.xpath("//a[@id='loginPageRegister1']");
    By getNew = By.id("loginPageGetNews");
    By loginError = By.xpath("//div[@class='alert alert-danger']");
    
    public PageLogin(WebDriver driver)
    {
        this.driver = driver;
    }
    
    public WebElement getUserName()
    {
    	return driver.findElement(userName);
    }

    public WebElement getPassword()
    {
    	return driver.findElement(passWord);
    }
    
    public WebElement getLoginButton()
    {
    	return driver.findElement(login);
    }
    
    public WebElement getGetNew()
    {
    	return driver.findElement(getNew);
    }
    
    public WebElement getRegister()
    {
    	return driver.findElement(register);
    }
    
    public WebElement getForgetPassword()
    {
    	return driver.findElement(forGetPwd);
    }
    /*
    public List<WebElement> getUserError()
    {
    	return driver.findElements(emailError);
    }
    
    public List<WebElement> getPasswordError()
    {
    	return driver.findElements(passwordError);
    } */
    
    public List<WebElement> getLoginError()
    {
    	return driver.findElements(loginError);
    }
    

    //This method will be exposed to login in the application to test other pages for future.

    public void loginTo(String strUserName,String strPasword)
    {   
        if(strUserName.equals(""))
        	this.getUserName().clear();
        
        if(strPasword.equals(""))
        	this.getPassword().clear();
        
        
    	this.getUserName().sendKeys(strUserName);   

        this.getPassword().sendKeys(strPasword);  

        this.getLoginButton().click();       
    }

       
}


