package executeTestCases;

import org.testng.annotations.Test;

import pageObjects.PageLogin;
import pageUIOperations.GetNewPageUIOperation;
import readExcelSheet.ReadExcelFile;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(listernerAndLog.ListenerTest.class)

public class ExecuteTestCasesGetNewPage {
	
	WebDriver driver = null;  
	Properties config = null;
	
	@BeforeTest
    public void setup() throws IOException
	{   	
		config = new Properties();
	    
        InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\Config.properties"));
        config.load(stream);
		String browser = config.getProperty("Browser");
						
		//setup the browser 
		if(browser.equalsIgnoreCase("firefox"))
        {  	System.setProperty("webdriver.firefox.marionette", ".\\Webdrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}			
		else if(browser.equalsIgnoreCase("chrome"))
		{   System.setProperty("webdriver.chrome.driver", ".\\Webdrivers\\chromedriver.exe");	
			driver = new ChromeDriver();
		}		
		else if(browser.equalsIgnoreCase("Edge"))
		{	System.setProperty("webdriver.edge.driver",".\\Webdrivers\\MicrosoftWebDriver.exe");							
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("IE"))
		{	System.setProperty("webdriver.ie.driver",".\\Webdrivers\\IEDriverServer.exe");							
		    driver = new InternetExplorerDriver();
		}
						
		else
		{ try 
		  {  throw new Exception("Browser is not correct");}
		  catch (Exception e) 
		  {e.printStackTrace();}
		}
		
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(config.getProperty("URL"));         
    }
	
    
	@Test(dataProvider="TestCasesSheet")
	public void testGetNewPage( String errorCondition, String firstName ,String lastName, String email, String board, String role, String message ) throws Exception
    {   
	 PageLogin login = new PageLogin(driver);
	 login.getGetNew().click(); 

       GetNewPageUIOperation operation = new GetNewPageUIOperation(driver);
       
       //Call perform function to perform operation on UI
       operation.perform (errorCondition, firstName , lastName, email, board, role, message ) ;    
 
    }

   @AfterTest
   public void closeBrower()
   {
	driver.close();          
   }


   @DataProvider(name="TestCasesSheet")
   public Object[][] getDataFromDataprovider() 
   {
    
	Object[][] object = null;
	
	try {
    ReadExcelFile file = new ReadExcelFile();
   
    //Read test cases sheet
    String testFile = config.getProperty("TestDataFile");
    String testSheet = config.getProperty("DataSheetName-FirstSheet");
    Sheet Sheet = file.readExcel( System.getProperty("user.dir") +"\\"+ testFile , testSheet);
    
   //Find number of rows and colums in excel file
    int rowCount = Sheet.getLastRowNum()-Sheet.getFirstRowNum();
    int colCount = Sheet.getRow(0).getLastCellNum();
  
    object = new Object[rowCount][colCount-1];
    for (int i = 0 ; i < rowCount; i++) 
     {
        Row row = Sheet.getRow(i+1);
        
        //Create a loop to print cell values in a row
        for (int j = 1; j < colCount; j++) 
        {    object[i][j-1] = row.getCell(j, Row.CREATE_NULL_AS_BLANK).toString();
        }
     }
   }
	catch (IOException e) {e.printStackTrace();}
	
    return object;    
  }
        
   
}



