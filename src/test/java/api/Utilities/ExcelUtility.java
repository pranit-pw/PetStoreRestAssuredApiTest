package api.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fileIn;
	public FileOutputStream fileOut;
	public XSSFWorkbook  workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle style;
	String path;
	
	
	
	public ExcelUtility(String path) {
		 
		this.path = path;
	}
	
	
	
	public int getRowCount(String sheetName) throws IOException {
		
		fileIn = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileIn);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fileIn.close();
		return rowCount;

	}
	
	
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		
		fileIn = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileIn);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fileIn.close();
		return cellCount;
		
	}
	
	
	
	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
		
		fileIn = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileIn);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try
		{
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data = "";
		}
		
		workbook.close();
		fileIn.close();
		return data;
	}
	
	
	
	public void setCellData(String sheetName, int rowNum, int cellNum, String data) throws IOException {
		
		File xlFile = new File(path);
		
		if(!xlFile.exists())
		{
			workbook = new XSSFWorkbook();
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		}
		
		fileIn = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileIn);
		
		if(workbook.getSheetIndex(sheetName)== -1)
		   workbook.createSheet(sheetName);
		   sheet = workbook.getSheet(sheetName);
		   
		if(sheet.getRow(rowNum)==null)
		   sheet.createRow(rowNum);
		row = sheet.getRow(rowNum);
		
		cell = row.createCell(cellNum);
		cell.setCellValue(data);
		fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		workbook.close();
		fileIn.close();
		fileOut.close();
	}
	

}
