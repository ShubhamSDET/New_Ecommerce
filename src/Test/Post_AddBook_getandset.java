package Test;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.*;

import Pojo.Post_AddBook;
import Pojo.Post_AddBook_response;
public class Post_AddBook_getandset {
	
	public static void main(String[] args) {
		
		Post_AddBook ad= new Post_AddBook();
		ad.setName("Learn Appium Automation with Java");
		ad.setIsbn("ccimmsds");
		ad.setAisle(227);
		ad.setAuthor("Shubham Sharma");
		
		RestAssured.baseURI="http://216.10.245.166";
		Post_AddBook_response mainres=given()
				.body(ad).expect().defaultParser(Parser.JSON)
		.when().post("/Library/Addbook.php").as(Post_AddBook_response.class);
		//.then().assertThat().statusCode(200).extract().response().as(Post_AddBook_response.class);		
		
		//System.out.println(mainres);
		
		System.out.println(mainres.getID());
		System.out.println(mainres.getMsg());
		
		

}
}