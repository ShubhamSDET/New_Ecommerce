package Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.AddPlacePayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class First {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			RestAssured.baseURI="https://rahulshettyacademy.com/";
			String response=given().log().all().queryParam("Key", "qaclick123").header("Content-Type","application/json").body(AddPlacePayload.AddPlace())
			.when().post("maps/api/place/add/json")
			.then().assertThat().log().all().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
			
			JsonPath js = new JsonPath(response);
			String placeId=js.getString("place_id");
			System.out.println(placeId);
			
			//String putaddress="Main Address, USA";
			given().log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json").body("{\r\n"
					+ "\"place_id\":\""+placeId+"\",\r\n"
					+ "\"address\":\"Summer walk, Africa\"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ "")
			.when().put("maps/api/place/update/json")
			.then().assertThat().log().all().statusCode(200);
			//.body("msg", equalTo("Address successfully updated"));
			
			String getting = given().log().all().queryParam("Key", "qaclick123").queryParam("place_id",placeId)
			.when().get("maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();

			JsonPath jsnew = new JsonPath(getting);
			String ActualAddress = jsnew.getString("address");
			System.out.println(ActualAddress);
			
			
		
	}

}
