package Test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured.*;
import io.restassured.filter.session.SessionFilter;
import io.restassured.RestAssured;

public class Jira_Add_Comment {
	
	@Test
	public void AddComment() {
		SessionFilter session=new SessionFilter();
		RestAssured.baseURI="http://localhost:8090";
		String response=given().header("Content-Type","application/json").body("{ \"username\": \"shubhamsharma\", \"password\": \"Shubh@m8\" }").log().all().filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();
		
		given().pathParam("Key", "10001").log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \"Adding a comment from API\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{Key}/comment")
		.then().log().all().assertThat().statusCode(201);
		
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("Key", "10001")
		.header("Content-Type","multipart/form-data").multiPart("file", new File("C:\\Users\\shubhamsharma11\\eclipse-workspace\\RestAPI\\src\\Files\\Jira.txt"))
		.when().post("/rest/api/2/issue/{Key}/attachments").then().log().all().assertThat().statusCode(200);
		
	}

}
