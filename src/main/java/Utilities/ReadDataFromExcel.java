package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel
{
	public ArrayList<String> readExcelData(String sheetName, String testCaseName) throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\Sayan Ghosh Dastidar\\RestAssuredLearning\\RestAssuredFrameworkExcelData\\testdata.xlsx");
		ArrayList <String> al=new ArrayList<>();
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		int countOfSheets=workbook.getNumberOfSheets();
		for(int i=0; i<countOfSheets; i++)
		{
			XSSFSheet sheet=workbook.getSheetAt(i);
			if(sheet.getSheetName().equalsIgnoreCase(sheetName))
			{
				Iterator <Row> rows=sheet.iterator();
				Row headerRow=rows.next();
				Iterator <Cell> headerRowCells=headerRow.cellIterator();
				int columnNumber=0;
				while(headerRowCells.hasNext())
				{
					Cell headerRowTargetCell=headerRowCells.next();
					if(headerRowTargetCell.getStringCellValue().equalsIgnoreCase("Test Cases"))
						break;
					columnNumber++;
				}
				while(rows.hasNext())
				{
					Row testCaseRow = rows.next();
					Cell testCaseNameCell=testCaseRow.getCell(columnNumber);
					if(testCaseNameCell.getStringCellValue().equalsIgnoreCase(testCaseName))
					{
						Iterator <Cell> testCaseCells=testCaseRow.cellIterator();
						while(testCaseCells.hasNext())
						{
							Cell targetCell=testCaseCells.next();
							al.add(targetCell.getStringCellValue());
						}
					}
				}
			}
		}
		return al;
	}
}