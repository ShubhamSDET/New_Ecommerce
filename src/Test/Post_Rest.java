package Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Post_Rest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String address = "111 New home";
		RestAssured.baseURI="http://rahulshettyacademy.com/";
		String convert=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \""+address+"\",\r\n"
				+ "  \"phone_number\": \"(+91) 8076590824\",\r\n"
				+ "  \"address\": \"48, BPCL Apt, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://shubham.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		JsonPath js = new JsonPath(convert);
		String id=js.getString("place_id");
		System.out.println(id);
	}

}
