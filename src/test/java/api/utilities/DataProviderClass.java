package api.utilities;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {

	String path = System.getProperty("user.dir")+ "\\target\\Test_Data\\API_Test_Data.xlsx";
	ExcelRead xlReader = new ExcelRead(path);
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException	{

	int rownum=xlReader.getRowCount ("APIData");
	int colcount=xlReader.getCellCount ("APIData",1);
	String apidata[][]=new String[rownum][colcount];
	for(int i=1;i<=rownum;i++)
	{
	for(int j=0;j<colcount;j++)
	{
	apidata[i-1][j]= xlReader.getCellData("APIData",i, j);
	}
	
	}
	return apidata;
	}

@DataProvider (name="UserNames")
	public String[] getUserNames() throws IOException
	{
	
	int rownum=xlReader.getRowCount ("APIData");
	String apidata[]=new String[rownum];
	for (int i=1;i<=rownum;i++)
	{
	apidata[i-1]= xlReader.getCellData("APIData", i, 1);
	}
	
	return apidata;
	}
	
	

}
