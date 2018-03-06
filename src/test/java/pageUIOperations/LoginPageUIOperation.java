package pageUIOperations;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pageObjects.PageLogin;

public class LoginPageUIOperation extends PageLogin {
	
	public LoginPageUIOperation (WebDriver driver)
	{		   super(driver);	
	           
	}

	
	public void perform(String messageType, String userName ,String passwd, String message ) throws Exception
    {      
        String returnMsg ;
        
        switch (messageType.toUpperCase()) 
        {
        
        case "GETNEW": 
        	//click GetNew should go to New sign up page  
        	getGetNew().click();  	
        	returnMsg = driver.getCurrentUrl();
            Assert.assertEquals( returnMsg, message );
            break;
        
        case "REGISTERVERIFY": 
        	//click register button should go to register page  
        	getRegister().click();
        	returnMsg = driver.getCurrentUrl();
            Assert.assertEquals( returnMsg, message );
            driver.navigate().back();
            break;
            
        case "FORGETPASSWORD":
        	//click forget password button should go to forget password page
        	getForgetPassword().click();
        	returnMsg = driver.getCurrentUrl();
            Assert.assertEquals( returnMsg, message ); 
            driver.navigate().back();
            break;
            
        case "LOGINFAIL":
            //check login error message
        	loginTo(userName, passwd);	
        	Assert.assertTrue(verifyMutipleFailure(message));
            break;        
            
        default:
            break;
        }  
             
    }	
	
	/* VerifyMutipleFailure functions verify the error messages and match the number of messages.
	 * if message not match with expected result or the message number is not correct, return false
	*/
	public boolean verifyMutipleFailure(String mesg)
    {			
        int listSize = getLoginError().size();
        boolean errorMatch = true;
        int count = 0;
   
        List<String> list = Arrays.asList(mesg.split(";"));
 	
    	if(listSize >0)
    	{	
    		String error ;
    	    for ( int i=0; i<listSize; i++)
    	    { count++;
    	      error = getLoginError().get(i).getText();   
    		  if(!mesg.contains(error) )
    		  {   errorMatch= false; 	
    		        break;
    		  }    
    		  
    	    }  
    	}
	
    	return (errorMatch && count == list.size() ); 		
   
    }
	
	
}
