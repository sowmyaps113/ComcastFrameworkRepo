package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcelFile(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		FileInputStream fis=new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowNum=wb.getSheet(sheetName).getLastRowNum();
		return rowNum;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws IOException
	{
		FileInputStream fis=new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		FileOutputStream fos=new FileOutputStream("./testdata/testScriptdata.xlsx");
		wb.write(fos);
		wb.close();
	}
}
