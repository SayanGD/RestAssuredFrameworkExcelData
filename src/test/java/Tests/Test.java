package tests;

import java.io.IOException;
import java.util.ArrayList;
import utilities.ReadDataFromExcel;

public class Test
{

	public static void main(String[] args) throws IOException
	{
		ReadDataFromExcel readData=new ReadDataFromExcel();
		ArrayList<String> al=readData.readExcelData("TestData", "Add Profile");
		for(String s:al)
			System.out.println(s);
	}
}