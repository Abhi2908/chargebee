package bid.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFile {
	static final Logger logger = Logger.getLogger(ReadFile.class.getName());
	
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	
	public static List<Object> getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception
	{   
		String[][] tabArray = null;
		List<Object> itemList = new ArrayList<Object>();
		try{
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int startCol = 0;
			int ci=0,cj=0;
			int totalRows = 1;
			int totalCols = 4;
			tabArray=new String[totalRows][totalCols];
			for (int j=startCol;j<totalCols;j++, ++cj)
			{
				tabArray[ci][cj]=getCellData(iTestCaseRow,j);
				itemList.add(tabArray[ci][cj]);
				
				System.out.println(tabArray[ci][cj]);
				//logger.info(tabArray[ci][cj]);
			}
		}
		catch (FileNotFoundException e)
		{
			logger.info("Could not read the Excel sheet");
			logger.info(e.toString());
		}
		catch (IOException e)
		{
			logger.info("Could not read the Excel sheet");
			logger.info(e.toString());
		}
		return(itemList);
	}
	
	public static String getCellData(int RowNum, int ColNum) throws Exception{
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = null;
			if(Cell.getCellType()==1) 
				CellData = Cell.getStringCellValue(); 
			else if(Cell.getCellType()==0) 
				CellData = String.valueOf(Cell.getNumericCellValue());
			
			
			return CellData;
		}catch (Exception e){
			logger.info(e.toString());
			return"";
		}
	}	
	
}
