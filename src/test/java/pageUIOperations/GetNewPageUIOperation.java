package pageUIOperations;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pageObjects.PageGetNews;

public class GetNewPageUIOperation extends PageGetNews
{  
    public GetNewPageUIOperation(WebDriver driver)
    {    super(driver);
    }
    
    public void perform(String messageType, String firstName ,String lastName, String email, String board, String role, String message ) throws Exception
    {      
        getFirstName().sendKeys(firstName);
        getLastName().sendKeys(lastName);
        getEmail().sendKeys(email);
        
        if (!board.equals(""))
          getBoard().selectByVisibleText(board);
        
        if (!role.equals("") )   
        	getRole().selectByVisibleText(role);
              
        
        getSubmitButton().click();
        
                                  
        switch (messageType.toUpperCase()) 
        {
        case "FIRSTNAMEERROR":
            //check first name error message
            Assert.assertEquals(getFirstNameError().get(0).getText(), message );
            break;
            
        case "LASTNAMEERROR":
        	//check last name error message
        	Assert.assertEquals(getLastNameError().get(0).getText(), message );
            break;
            
        case "EMAILERROR":
            //check email error message
        	Assert.assertEquals(getEmailError().get(0).getText(), message );
            break;    
            
        case "ROLEERROR":
            //check role error message
        	Assert.assertEquals(getRoleError().get(0).getText(), message );
            break; 
            
        case "SUCCESS":
            //check successful message
        	Assert.assertEquals(getSuccessMessage().get(0).getText(), message );
            break; 
        
        case "MUTIPLEERROR":
            //check mutiple error message
        	Assert.assertTrue(verifyMutipleFaiure(message));
            break; 
            
        default:
            break;
        }  
        
        getClose().click();
    }
    
    public boolean verifyMutipleFaiure(String mesg)
    {
    	List<String> list = Arrays.asList(mesg.split(";"));
    	boolean inList = true; 
    	int count = 0 ; 
    	  	    	
    	if(getFirstNameError().size()>0)	
    	{	inList = mesg.contains(getFirstNameError().get(0).getText()); 
    	    count++; 
    	}
    	 		
    	if(getLastNameError().size()>0)	
    	{	inList = mesg.contains(getLastNameError().get(0).getText());
    	    count++;
    	}
   	
    	if(getEmailError().size()>0)
    	{	inList = mesg.contains(getEmailError().get(0).getText());
    	    count++;
    	}
    	    	
    	if(getRoleError().size()>0)
    	{	inList = mesg.contains(getRoleError().get(0).getText());
    	    count++;
    	}
    	   	
    	//if(getSuccessMessage().size()>0 && getSuccessMessage().get(0).getText().equals(mesg) )   			
    	//{	return true;   	   }
    	
        return (inList && count==list.size());  
    		   	
    }
            
}