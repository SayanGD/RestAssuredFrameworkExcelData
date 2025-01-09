package tests;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class AddBookTest
{
	@Test
	public void addBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		String addBookResponse=given().log().all().contentType(ContentType.JSON).body("{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\"abcd\",\r\n"
				+ "\"aisle\":\"414141\",\r\n"
				+ "\"author\":\"Sayan GD\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(addBookResponse);
		System.out.println(js.getString("ID"));
	}
}