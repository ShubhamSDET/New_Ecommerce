package Test;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class Jira2 {
	
	@Test
	public void GetBugDetails() {
		SessionFilter session=new SessionFilter();
		RestAssured.baseURI="http://localhost:8090";
		String response=given().header("Content-Type","application/json").body("{ \"username\": \"shubhamsharma\", \"password\": \"Shubh@m8\" }").log().all().filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();
		
		//Add Comment
		String expectedcomment = "Hi new comment adding";
		String addingcomment=given().pathParam("Key", "10001").log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \""+expectedcomment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{Key}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js=new JsonPath(addingcomment);
		String commmentid=js.get("id");
		
		String allresponse=given().filter(session).pathParam("Key", "10001").queryParam("fields", "comment").when().get("/rest/api/2/issue/{Key}")
		.then().log().all().extract().response().asString();
		
		JsonPath js1=new JsonPath(allresponse);
		int arraycount=js1.getInt("fields.comment.comments.size()");
		for(int i=0;i<arraycount;i++) {
			String searchid=js1.get("fields.comment.comments["+i+"].id").toString();
			if(commmentid.equalsIgnoreCase(searchid)) {
				String bodymessage=js1.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(bodymessage);
				Assert.assertEquals(expectedcomment, bodymessage);
			}
		}
		

}
}