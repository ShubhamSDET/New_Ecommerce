package ReqRes_Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ReqRes_Pojo.GetALlUsers_Response;
import ReqRes_Pojo.GetAllUsersData_Response;
import ReqRes_Pojo.PostAddNewUser_Request;
import ReqRes_Pojo.PostAddNewUser_Response;
import ReqRes_Pojo.PutUpdateUserProfile_Request;
import ReqRes_Pojo.PutUpdateUserProfile_Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

public class Get_All_Users {
	RequestSpecification reqSpec;
	
	@BeforeMethod
	public void Request() {
		
		RequestSpecBuilder builder = new RequestSpecBuilder().setBaseUri("https://reqres.in").setContentType(ContentType.JSON);
		 reqSpec=builder.build();
		
	}
	
	@Test(enabled=false, priority=-1)
	public void getAllUsers() {
		
		GetALlUsers_Response allDetails=given().spec(reqSpec).queryParam("page", 2)
	.when().get("/api/users?page=2")
	.then().assertThat().statusCode(200).extract().response().as(GetALlUsers_Response.class);
		
		System.out.println(allDetails.getPage());
		System.out.println(allDetails.getPer_page());
		System.out.println(allDetails.getTotal());
		System.out.println(allDetails.getTotal_pages());
		
		System.out.println(allDetails.getSupport().getText());
		
		System.out.println(allDetails.getData().get(0).getAvatar());
		
		List<GetAllUsersData_Response> name=allDetails.getData();
		
		for(int i =0; i<name.size(); i++)
		{
			if(name.get(i).getFirst_name().equalsIgnoreCase("Rachel")) {
				
				System.out.println(name.get(i).getId());
			}
		}
		
		for(int i=0; i<name.size(); i++) 
		{
			System.out.println(name.get(i).getId());
		}
		
	}
	
	@Test(enabled=true,priority=0)
	public void addNewUser() {
		PostAddNewUser_Request newUser = new PostAddNewUser_Request();
		newUser.setName("Shiva");
		newUser.setJob("Leader");
		PostAddNewUser_Response newUserResponse=given().spec(reqSpec).body(newUser)
		.when().post("/api/users")
		.then().assertThat().statusCode(201).extract().response().as(PostAddNewUser_Response.class);
		
		System.out.println(newUserResponse.getId());
		System.out.println(newUserResponse.getCreatedAt());
		
	}
	
	@Test(enabled=true, priority=2, dependsOnMethods="addNewUser")
	public void updateUserProfile() {
		
		
		PutUpdateUserProfile_Response update=given().spec(reqSpec).body(new File("C:\\Users\\shubhamsharma11\\eclipse-workspace\\RestAPI\\src\\Files\\body.txt"))	
		.expect().defaultParser(Parser.JSON)		
		.when().put("/api/users/2")
		.then().assertThat().statusCode(200).extract().response().as(PutUpdateUserProfile_Response.class);
		
		
		System.out.println(update.getJob());
		System.out.println(update.getName());
		System.out.println(update.getUpdatedAt());
		
	}
	
	

}
