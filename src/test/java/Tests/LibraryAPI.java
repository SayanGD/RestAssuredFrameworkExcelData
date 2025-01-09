package tests;

import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class LibraryAPI
{
	@Test
	public void addBook()
	{
		HashMap <String, Object> jsonBody=new HashMap<>();
		jsonBody.put("name", "Learn Appium Automation with Java");
		jsonBody.put("isbn", "abcd");
		jsonBody.put("aisle", "41414141");
		jsonBody.put("author", "Sayan GD");

		RestAssured.baseURI="http://216.10.245.166";
		String addBookResponse=given().log().all().contentType(ContentType.JSON).body(jsonBody)
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(addBookResponse);
		System.out.println(js.getString("ID"));
	}
}