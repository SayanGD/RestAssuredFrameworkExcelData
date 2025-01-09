package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utilities.ReadDataFromExcel;

public class LibraryAPI
{
	String bookID;
	@Test
	public void addBook() throws IOException
	{
		ReadDataFromExcel readData=new ReadDataFromExcel();
		ArrayList<String> al=readData.readExcelData("Library API", "Add Book");

		HashMap <String, Object> jsonBody=new HashMap<>();
		jsonBody.put("name", al.get(1));
		jsonBody.put("isbn", al.get(2));
		jsonBody.put("aisle", al.get(3));
		jsonBody.put("author", al.get(4));

		RestAssured.baseURI="http://216.10.245.166";
		String addBookResponse=given().log().all().contentType(ContentType.JSON).body(jsonBody)
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).extract().response().asString();

		JsonPath js=new JsonPath(addBookResponse);
		bookID=js.getString("ID");
	}

	@Test
	public void deleteBook()
	{
		HashMap <String, Object> jsonBody=new HashMap<>();
		jsonBody.put("ID", bookID);

		RestAssured.baseURI="http://216.10.245.166";
		String deleteBookResponse=given().log().all().contentType(ContentType.JSON).body(jsonBody)
		.when().delete("Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted")).extract().response().asString();
	}
}