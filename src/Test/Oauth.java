package Test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pojo.Actual_Response;
import Pojo.webAutomation;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;


public class Oauth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] expected = {"Selenium Webdriver Java","Cypress","Protractor"};
		String code ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWgavdeTkEaf3y4OPjGDaqLqN_N0j2bI39hOJxZJFNPhleAaWiityHRNO84XmlnYpcI3NA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String codeSplit=code.split("code=")[1];
		
		String actualCode=codeSplit.split("&scope")[0];
		System.out.println(actualCode);
		
		String normalToken=given().urlEncodingEnabled(false)
				.queryParam("code", actualCode)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").then().log().all()
		.extract().response().asString();
		JsonPath js= new JsonPath(normalToken);
		String token=js.get("access_token");
		System.out.println(token);
		
		Actual_Response  actualResponse=given().queryParam("access_token", token).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(Actual_Response.class);
		System.out.println(actualResponse.getInstructor());
		System.out.println(actualResponse.getLinkedIn());
		
		
		ArrayList<String> a= new ArrayList<String>();
		List<webAutomation> allweb=actualResponse.getCourses().getWebAutomation();
		for(int i=0;i<allweb.size();i++) {
			a.add(allweb.get(i).getCourseTitle());
		}
		
		List<String> expectedlist=Arrays.asList(expected);
		Assert.assertEquals(a, expectedlist);

	}

}
