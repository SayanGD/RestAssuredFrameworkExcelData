package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utilities.ReadDataFromExcel;
import utilities.SpecBuilder;

public class LibraryAPI extends SpecBuilder
{
	String bookID;
	@Test(priority = 1)
	public void addBook() throws IOException
	{
		ReadDataFromExcel readData=new ReadDataFromExcel();
		ArrayList<String> al=readData.readExcelData("Library API", "Add Book");

		HashMap <String, Object> jsonBody=new HashMap<>();
		jsonBody.put("name", al.get(1));
		jsonBody.put("isbn", al.get(2));
		jsonBody.put("aisle", al.get(3));
		jsonBody.put("author", al.get(4));
	
		String addBookResponse=given().log().all().spec(requestSpecBuilder()).body(jsonBody)
		.when().post("Library/Addbook.php")
		.then().log().all().spec(responseSpecBuilder()).body("Msg", equalTo("successfully added")).extract().response().asString();

		JsonPath js=new JsonPath(addBookResponse);
		bookID=js.getString("ID");
	}

	@Test(priority = 2)
	public void getBook()
	{
		given().log().all().spec(requestSpecBuilder()).queryParam("ID", bookID)
		.when().get("Library/GetBook.php")
		.then().log().all().spec(responseSpecBuilder());
	}

	@Test(priority = 3)
	public void deleteBook()
	{
		HashMap <String, Object> jsonBody=new HashMap<>();
		jsonBody.put("ID", bookID);

		String deleteBookResponse=given().log().all().spec(requestSpecBuilder()).body(jsonBody)
		.when().delete("Library/DeleteBook.php")
		.then().log().all().spec(responseSpecBuilder()).body("msg", equalTo("book is successfully deleted")).extract().response().asString();
	}
}