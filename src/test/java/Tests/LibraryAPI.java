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
	
		String addBookResponse=given().log().all().spec(requestSpecBuilder()).body(jsonBody)
		.when().post("Library/Addbook.php")
		.then().log().all().spec(responseSpec()).assertThat().statusCode(200).body("Msg", equalTo("successfully added")).extract().response().asString();

		JsonPath js=new JsonPath(addBookResponse);
		bookID=js.getString("ID");
	}

	@Test
	public void deleteBook()
	{
		HashMap <String, Object> jsonBody=new HashMap<>();
		jsonBody.put("ID", bookID);

		String deleteBookResponse=given().log().all().spec(requestSpecBuilder()).body(jsonBody)
		.when().delete("Library/DeleteBook.php")
		.then().log().all().spec(responseSpec()).body("msg", equalTo("book is successfully deleted")).extract().response().asString();
	}
}