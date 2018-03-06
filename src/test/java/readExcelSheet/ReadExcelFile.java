package readExcelSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelFile {

    public Sheet readExcel(String fileName,String sheetName) throws IOException
    {    	
    //Create a object of File class to open xlsx file 
    File file = new File(fileName);  
    FileInputStream inputStream = new FileInputStream(file);
    Workbook Workbook = new XSSFWorkbook(inputStream);
       
    //Read sheet inside the workbook by its name
    Sheet sheet = Workbook.getSheet(sheetName);
    return sheet;    
    }
    
}    
