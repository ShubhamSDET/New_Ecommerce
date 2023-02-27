package Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.Post_AddBook_Serialize;
import Pojo.Post_AddBook_location;
public class Post_AddPlace_getandset2 {
	
	public static void main(String[] args) {
		
		Post_AddBook_Serialize p = new Post_AddBook_Serialize();
		p.setAccuracy(50);
		p.setAddress("london,UK");
		p.setName("New house");
		p.setPhone_number("1111111111");
		p.setWebsite("http://shubham.com");
		p.setLanguage("English");
		
		Post_AddBook_location l = new Post_AddBook_location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		
		p.setTypes(mylist);
		
		String res=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(p)
		.when().post("https://rahulshettyacademy.com/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(res);
		

}
}