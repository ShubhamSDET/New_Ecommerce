package Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Pojo.Ecommerce_ArrayOrders;
import Pojo.Ecommerce_CreateOrder;
import Pojo.Ecommerce_req_login;
import Pojo.Ecommerce_response_login;

public class Ecommerce_Login {

	public static void main(String[] args) {
		
		//Login with Token
	
		Ecommerce_req_login lr= new Ecommerce_req_login();
		lr.setUserEmail("shubhamsharmqa@gmai.com");
		lr.setUserPassword("Shubh@m8");
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		Ecommerce_response_login rs=given().log().all().spec(req).body(lr)
		.when().post("/api/ecom/auth/login")
		.then().extract().response().as(Ecommerce_response_login.class);
		String token = rs.getToken();
		String User_id= rs.getUserId();
		
		//Create Product
		
		RequestSpecification reqs=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
		
		String create_product=given().log().all().spec(reqs).param("productName", "laptops").param("productAddedBy", User_id).param("productCategory", "Electronics")
		.param("productSubCategory", "lap").param("productPrice", "111500").param("productDescription", "Apple_laptop")
		.param("productFor", "For_all_gnders").multiPart("productImage", new File("C:\\Users\\shubhamsharma11\\OneDrive - Nagarro\\Desktop\\laptop.png"))
		.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
		JsonPath js = new JsonPath(create_product);
		String productid=js.get("productId");
		System.out.println(productid);
		
		RequestSpecification createOrderReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token)
				.setContentType(ContentType.JSON).build();
		
		//Create Order
		
		Ecommerce_ArrayOrders liOrders =  new Ecommerce_ArrayOrders();
		liOrders.setCountry("India");
		liOrders.setProductOrderedId(productid);
		
		List<Ecommerce_ArrayOrders> orderslist = new ArrayList<Ecommerce_ArrayOrders>();
		orderslist.add(liOrders);
		
		Ecommerce_CreateOrder order = new Ecommerce_CreateOrder();
		order.setOrders(orderslist);
		String orderdetails=given().log().all().spec(createOrderReq).body(order)
		.when().post("/api/ecom/order/create-order")
		.then().log().all().extract().response().asString();
		System.out.println(orderdetails);
		
		
		
		
		
	}

}
