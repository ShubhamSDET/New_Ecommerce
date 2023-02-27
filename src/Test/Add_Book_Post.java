package Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.AddPlacePayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Add_Book_Post {
	
	@Test(dataProvider="Bookdata")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String newadd=given().log().all().header("Content-Type","text/plain")
		.body(AddPlacePayload.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		
		JsonPath jas= new JsonPath(newadd);
		System.out.println(jas.get("ID"));
		
	}
	
	@DataProvider(name="Bookdata")
	public Object[][] getdata() {
	return	new Object[][] {{"bsadia","835732"},{"masjdb","0944"},{"qsnd","39025"}};
	}

}

