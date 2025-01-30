package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utilities.APIEndPoints;
import utilities.ReadDataFromExcel;
import utilities.SpecBuilder;

public class LibraryAPI extends SpecBuilder
{
	String bookID;
	String bookName;
	String isbn;
	String aisle;
	String author;

	@Test(priority = 1)
	public void addBook() throws IOException
	{
		ReadDataFromExcel readData=new ReadDataFromExcel();
		ArrayList<String> al=readData.readExcelData("Library API", "Add Book");
		bookName=al.get(1);
		isbn=al.get(2);
		aisle=al.get(3);
		author=al.get(4);

		HashMap <String, Object> jsonBody=new HashMap<>();
		jsonBody.put("name", bookName);
		jsonBody.put("isbn", isbn);
		jsonBody.put("aisle", aisle);
		jsonBody.put("author", author);

		String resourcePath=APIEndPoints.valueOf("AddBook").getResource();
	
		String addBookResponse=given().log().all().spec(requestSpecBuilder()).body(jsonBody)
		.when().post(resourcePath)
		.then().log().all().spec(responseSpecBuilder()).body("Msg", equalTo("successfully added")).extract().response().asString();

		JsonPath js=new JsonPath(addBookResponse);
		bookID=js.getString("ID");
	}

	@Test(priority = 2)
	public void getBook()
	{
		String resourcePath=APIEndPoints.valueOf("GetBook").getResource();

		given().log().all().spec(requestSpecBuilder()).queryParam("ID", bookID)
		.when().get(resourcePath)
		.then().log().all().spec(responseSpecBuilder()).body("book_name[0]", equalTo(bookName)).body("isbn[0]", equalTo(isbn)).body("aisle[0]", equalTo(aisle));
	}

	@Test(priority = 3)
	public void deleteBook()
	{
		HashMap <String, Object> jsonBody=new HashMap<>();
		jsonBody.put("ID", "XYZ4141");

		String resourcePath=APIEndPoints.valueOf("DeleteBook").getResource();

		String deleteBookResponse=given().log().all().spec(requestSpecBuilder()).body(jsonBody)
		.when().delete(resourcePath)
		.then().log().all().spec(responseSpecBuilder()).body("msg", equalTo("book is successfully deleted")).extract().response().asString();
	}
}