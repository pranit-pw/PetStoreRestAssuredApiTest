package api.Utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import api.Utilities.ExcelUtility;
import org.testng.annotations.DataProvider;

@SuppressWarnings("unused")
public class DataProviders {
	
	
	@DataProvider(name = "allData")
	public Object[][] getAllData(Method method) throws IOException {
		
		String sheetName = getSheetName(method.getName());

        String path = System.getProperty("user.dir")+"//src//test//resources//testDataExcelFile//UserData.xlsx";
        
        ExcelUtility excel = new ExcelUtility(path);
        
        int rowNum = excel.getRowCount(sheetName);
        int cellNum = excel.getCellCount(sheetName, 1);
        
        
		String[][] apiData = new String[rowNum][cellNum];
        
        for(int i=1; i<=rowNum; i++)
        {
        	for(int j=0; j<cellNum; j++)
        	{
        		apiData[i-1][j] = excel.getCellData(sheetName, i, j);
        	}
        }
        
        return apiData;
	}
	
	
	@DataProvider(name = "singleColumn")
	public Object[] getUserNames(Method method) throws IOException {
		
		String sheetName = getSheetName(method.getName());
		
        String path = System.getProperty("user.dir")+"//src//test//resources//testDataExcelFile//UserData.xlsx";
        
        ExcelUtility excel = new ExcelUtility(path);
     
        int rowNum = excel.getRowCount(sheetName);
        
        String apiData[] = new String[rowNum];
        
        for(int i=1; i<=rowNum; i++)
        {
        	apiData[i-1] = excel.getCellData(sheetName, i, 1);
        }
        
        return apiData;     
        
	}
	
	private String getSheetName (String methodName) {
		
		switch (methodName) {
		
		case "testCreateMultipleUsers": return "UserList";
		case "testDeleteUserByUserName": return "UserList";
		case "addNewPetsInStore": return "PetList";
		case "deletePetsFromStore": return "PetList";
		
		default : 
			throw new IllegalArgumentException("No matching sheet name for method: " + methodName);
		}
	}
	
}
