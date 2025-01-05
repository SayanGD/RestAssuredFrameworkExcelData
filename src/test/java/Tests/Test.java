package Tests;

import java.io.IOException;
import Utilities.ReadDataFromExcel;

public class Test
{

	public static void main(String[] args) throws IOException
	{
		ReadDataFromExcel readData=new ReadDataFromExcel();
		readData.readExcelData("TestData", "Add Profile");
	}
}