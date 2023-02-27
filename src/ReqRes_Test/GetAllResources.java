package ReqRes_Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pojo.AllResources_Data_Response;
import Pojo.AllResources_Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.util.List;

public class GetAllResources {
	RequestSpecification rq;
	@BeforeTest
	public void request() {
		RequestSpecBuilder req = new RequestSpecBuilder();
		req.setBaseUri("https://reqres.in").setContentType(ContentType.JSON);
		rq = req.build();
	}
	@Test
	public void resources() {
		
		AllResources_Response allResources= given().spec(rq).when().get("/api/unknown").then().assertThat().statusCode(200)
		.extract().response().as(AllResources_Response.class);
		
		System.out.println(allResources.getSupport().getText());
		
		List<AllResources_Data_Response> allID=allResources.getData();
		for(int i=0; i<allID.size();i++) {
			System.out.println(allID.get(i).getId());
			System.out.println(allID.get(i).getColor());
		}
	}

}
