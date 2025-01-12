package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder
{
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;

	public RequestSpecification requestSpecBuilder()
	{
		requestSpec = new RequestSpecBuilder().setBaseUri("http://216.10.245.166").setContentType(ContentType.JSON).build();
		return requestSpec;
	}

	public ResponseSpecification responseSpec()
	{
		responseSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		return responseSpec;
	}
}