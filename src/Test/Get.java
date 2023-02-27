package Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Pojo.getAddress;

public class Get {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//RestAssured.baseURI = "https://rahulshettyacademy.com/";
		getAddress gets=given().queryParam("key", "qaclick123").queryParam("place_id", "e9924a3310f57631fdf87eab32c5e025")
				.expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/maps/api/place/get/json")
		.as(getAddress.class);
		
		System.out.println(gets.getAccuracy());
		System.out.println(gets.getLocation().getLatitude());
		
		
		
/*		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath jas=new JsonPath(gets);
		String random=jas.getString("address");
		System.out.println(random);
	*/	
		
		
	}

}
