package ReqRes_Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Ecommerce_Pojo.AddToCart_Products_Request;
import Ecommerce_Pojo.AddToCart_Request;
import Ecommerce_Pojo.AddToCart_Response;
import Ecommerce_Pojo.CreateProductWithoutImage_Response;
import Ecommerce_Pojo.CreateProductWithoutImage_Second_Response;
import Ecommerce_Pojo.CreateProduct_Response;
import Ecommerce_Pojo.DeleteOrder;
import Ecommerce_Pojo.InvalidToken_Response;
import Ecommerce_Pojo.Login_Request;
import Ecommerce_Pojo.Login_Response;
import Ecommerce_Pojo.OrderDetails_Response;
import Ecommerce_Pojo.PlaceOrder_Positive_Response;
import Ecommerce_Pojo.PlaceOrder_Request;
import Ecommerce_Pojo.PlaceOrder_Secondpojo_Request;
import Ecommerce_Pojo.PlaceOrder_WithoutProductID_Response;
import Pojo.webAutomation;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ecommerce_Test {
	RequestSpecification req;
	String token;
	String userId;
	String productId;
	String orderId;
	
	@BeforeMethod
public void request() {
		
		RequestSpecBuilder rq = new RequestSpecBuilder();
		req=rq.setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
	}
	
	@Test(priority=0, alwaysRun=true)
	public void validLogin() {
		
		Login_Request login_re = new Login_Request();
		login_re.setUserEmail("shubhamsharmqa@gmai.com");
		login_re.setUserPassword("Shubh@m8");
		
		Login_Response loginResponse=given().spec(req).body(login_re).when().post("/api/ecom/auth/login")
		.then().assertThat().statusCode(200).extract().response().as(Login_Response.class);
		
		token = loginResponse.getToken();
		System.out.println(token);
		userId = loginResponse.getUserId();
		System.out.println(userId);
		
	}
	
	@Test(priority=1, enabled=true)
	public void invalidLogin() 
	{
		Login_Request invalidLr = new Login_Request();
		invalidLr.setUserEmail("shubhamsharm@gmai.com");
		invalidLr.setUserPassword("Shubh@m8");
		
		Login_Response invalidLoginResponse=given().baseUri("https://rahulshettyacademy.com").body(invalidLr).when().post("/api/ecom/auth/login")
		.then().assertThat().statusCode(400).extract().response().as(Login_Response.class);
		
		String actualMessage=invalidLoginResponse.getMessage();
		
		String expectedMessage="Email is required";
		Assert.assertEquals(actualMessage, expectedMessage);
		
	}
	
	@Test(priority=2, enabled=true)
	public void createProduct() {
	//	Login_Response usersId = new Login_Response();
		//String id=usersId.getUserId();
		
		CreateProduct_Response cr= given().baseUri("https://rahulshettyacademy.com").header("Authorization",token)
				.param("productName", "Apple laptop").param("productAddedBy", userId)
		.param("productCategory", "Electronics").param("productSubCategory", "Laptops").param("productPrice", "215000")
		.param("productDescription", "New_Laptop").param("productFor", "All_genders")
		.multiPart("productImage",new File("C:\\Users\\shubhamsharma11\\OneDrive - Nagarro\\Desktop\\laptop.png"))
		.when().post("/api/ecom/product/add-product").then().assertThat().statusCode(201)
		.extract().response().as(CreateProduct_Response.class);		
		
		productId=cr.getProductId();
		System.out.println(productId);
	}
	
	@Test(priority=3, enabled=true)
	public void createProductWithoutToken() {
		
		InvalidToken_Response it= given().baseUri("https://rahulshettyacademy.com")
				.param("productName", "Apple laptop").param("productAddedBy", userId)
		.param("productCategory", "Electronics").param("productSubCategory", "Laptops").param("productPrice", "215000")
		.param("productDescription", "New_Laptop").param("productFor", "All_genders")
		.multiPart("productImage",new File("C:\\Users\\shubhamsharma11\\OneDrive - Nagarro\\Desktop\\laptop.png"))
		.when().post("/api/ecom/product/add-product").then().assertThat().statusCode(401)
		.extract().response().as(InvalidToken_Response.class);
		
		System.out.println(it.getMessage());
		
	}
	
	@Test(priority=4, enabled=true)
	public void createProductInvalidToken() {
		
		InvalidToken_Response invalidTokenCreateProduct= given().baseUri("https://rahulshettyacademy.com")
		.header("Authorization","NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2IyYzIzOTAzODQxZTljOWE2NzczOGYiLCJ1c2VyRW1haWwiOiJzaHViaGFtc2hhcm1xYUBnbWFpLmNvbSIsInVzZXJNb2JpbGUiOjk5Nzc4ODMzNDQsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE2NzQ5MDAyMjEsImV4cCI6MTcwNjQ1NzgyMX0.7Qi0_CmmMg6lQXCMb9_J2Ee-lmG_AOFsMK6hilOyPv")
				.param("productName", "Apple laptop").param("productAddedBy", userId)
		.param("productCategory", "Electronics").param("productSubCategory", "Laptops").param("productPrice", "215000")
		.param("productDescription", "New_Laptop").param("productFor", "All_genders")
		.multiPart("productImage",new File("C:\\Users\\shubhamsharma11\\OneDrive - Nagarro\\Desktop\\laptop.png"))
		.when().post("/api/ecom/product/add-product").then().assertThat().statusCode(401)
		.extract().response().as(InvalidToken_Response.class);
		
		System.out.println(invalidTokenCreateProduct.getMessage());
		
	}
	
	@Test(priority=5, enabled=true)
	public void createProductWithoutImage() {
		
		CreateProductWithoutImage_Response cnr = given().log().all().baseUri("https://rahulshettyacademy.com").header("Authorization",token)
				.param("productName", "").param("productAddedBy", userId)
		.param("productCategory", "Electronics").param("productSubCategory", "Laptops").param("productPrice", "115000")
		.param("productDescription", "Laptops").param("productFor", "All_genders").multiPart("productImage",new File("C:\\Users\\shubhamsharma11\\OneDrive - Nagarro\\Desktop\\laptop.png"))	
		.when().post("/api/ecom/product/add-product").then().log().all().assertThat().statusCode(500)
		.extract().response().as(CreateProductWithoutImage_Response.class);		
		
		System.out.println(cnr.getMessage().get_message());
		System.out.println(cnr.getMessage().getErrors().getProductName().getProperties().getType());
		System.out.println(cnr.getMessage().getErrors().getProductName().getProperties().getValue());
		
	}
	
	@Test(priority=6, enabled=true)
	public void addToCart() {
		
		AddToCart_Request addproduct = new AddToCart_Request();
		addproduct.set_id(userId);
		
		AddToCart_Products_Request addProducts = new AddToCart_Products_Request();
		addProducts.set_id("63bd49f4568c3e9fb1f2db10");
		addProducts.setProductName("Apple_Laptop");
		addProducts.setProductCategory("Electronics");
		addProducts.setProductSubCategory("Laptops");
		addProducts.setProductPrice(115000);
		addProducts.setProductDescription("Apple_laptop");
		addProducts.setProductImage("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1673349620645.png");
		addProducts.setProductRating("0");
		addProducts.setProductTotalOrders("0");
		addProducts.setProductStatus("true");
		addProducts.setProductFor("All");
		addProducts.setProductAddedBy(userId);
		addProducts.set__v(0);
		
		addproduct.setProduct(addProducts);
		
		AddToCart_Response addResponse=given().spec(req).header("Authorization", token)
				.body(addproduct).when().post("/api/ecom/user/add-to-cart")
		.then().assertThat().statusCode(200).extract().response().as(AddToCart_Response.class);
		
		System.out.println(addResponse.getMessage());
		
	}
	
	@Test(priority=7, enabled=true)
	public void addToCartWithoutToken() {
		
		AddToCart_Request addproduct = new AddToCart_Request();
		addproduct.set_id(userId);
		
		AddToCart_Products_Request addProducts = new AddToCart_Products_Request();
		addProducts.set_id("63bd49f4568c3e9fb1f2db10");
		addProducts.setProductName("Apple_Laptop");
		addProducts.setProductCategory("Electronics");
		addProducts.setProductSubCategory("Laptops");
		addProducts.setProductPrice(115000);
		addProducts.setProductDescription("Apple_laptop");
		addProducts.setProductImage("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1673349620645.png");
		addProducts.setProductRating("0");
		addProducts.setProductTotalOrders("0");
		addProducts.setProductStatus("true");
		addProducts.setProductFor("All");
		addProducts.setProductAddedBy(userId);
		addProducts.set__v(0);
		
		addproduct.setProduct(addProducts);
		
		InvalidToken_Response addToCartWithoutToken=given().spec(req)
				.body(addproduct).when().post("/api/ecom/user/add-to-cart")
		.then().assertThat().statusCode(401).extract().response().as(InvalidToken_Response.class);
		
		System.out.println(addToCartWithoutToken.getMessage());
		
	}
	
	@Test(priority=8, enabled=true)
	public void addToCartInvalidToken() {
		
		AddToCart_Request addproduct = new AddToCart_Request();
		addproduct.set_id(userId);
		
		AddToCart_Products_Request addProducts = new AddToCart_Products_Request();
		addProducts.set_id("63bd49f4568c3e9fb1f2db10");
		addProducts.setProductName("Apple_Laptop");
		addProducts.setProductCategory("Electronics");
		addProducts.setProductSubCategory("Laptops");
		addProducts.setProductPrice(115000);
		addProducts.setProductDescription("Apple_laptop");
		addProducts.setProductImage("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1673349620645.png");
		addProducts.setProductRating("0");
		addProducts.setProductTotalOrders("0");
		addProducts.setProductStatus("true");
		addProducts.setProductFor("All");
		addProducts.setProductAddedBy(userId);
		addProducts.set__v(0);
		
		addproduct.setProduct(addProducts);
		
		InvalidToken_Response addToCartInvalidToken=given().spec(req).header("Authorization","NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2IyYzIzOTAzODQxZTljOWE2NzczOGYiLCJ1c2VyRW1haWwiOiJzaHViaGFtc2hhcm1xYUBnbWFpLmNvbSIsInVzZXJNb2JpbGUiOjk5Nzc4ODMzNDQsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE2NzQ5MDAyMjEsImV4cCI6MTcwNjQ1NzgyMX0.7Qi0_CmmMg6lQXCMb9_J2Ee-lmG_AOFsMK6hilOyPv")
				.body(addproduct).when().post("/api/ecom/user/add-to-cart")
		.then().assertThat().statusCode(401).extract().response().as(InvalidToken_Response.class);
		
		System.out.println(addToCartInvalidToken.getMessage());
		
	}
	
	@Test(priority=9, enabled=true)
	public void addingWithoutUserId() {
		
		AddToCart_Request addproduct = new AddToCart_Request();
		addproduct.set_id("");
		
		AddToCart_Products_Request addProducts = new AddToCart_Products_Request();
		addProducts.set_id("63bd49f4568c3e9fb1f2db10");
		addProducts.setProductName("Apple_Laptop");
		addProducts.setProductCategory("Electronics");
		addProducts.setProductSubCategory("Laptops");
		addProducts.setProductPrice(115000);
		addProducts.setProductDescription("Apple_laptop");
		addProducts.setProductImage("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1673349620645.png");
		addProducts.setProductRating("0");
		addProducts.setProductTotalOrders("0");
		addProducts.setProductStatus("true");
		addProducts.setProductFor("All");
		addProducts.setProductAddedBy("");
		addProducts.set__v(0);
		
		addproduct.setProduct(addProducts);
		
		AddToCart_Response addResponse=given().spec(req).header("Authorization", token)
				.body(addproduct).when().post("/api/ecom/user/add-to-cart")
		.then().assertThat().statusCode(400).extract().response().as(AddToCart_Response.class);
		
		System.out.println(addResponse.getMessage());
		
	}
	
	@Test(priority=10, enabled=true)
	public void addingWithoutProductId() {
		
		AddToCart_Request addproduct = new AddToCart_Request();
		addproduct.set_id(userId);
		
		AddToCart_Products_Request addProducts = new AddToCart_Products_Request();
		addProducts.set_id("");
		addProducts.setProductName("adidas original");
		addProducts.setProductCategory("fashion");
		addProducts.setProductSubCategory("shoes");
		addProducts.setProductPrice(8000);
		addProducts.setProductDescription("Shoes_for_Boys");
		addProducts.setProductImage("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1650649488046.jpg");
		addProducts.setProductRating("0");
		addProducts.setProductTotalOrders("0");
		addProducts.setProductStatus("true");
		addProducts.setProductFor("Men");
		addProducts.setProductAddedBy(userId);
		addProducts.set__v(0);
		
		addproduct.setProduct(addProducts);
		
		AddToCart_Response addResponse=given().spec(req).header("Authorization", token)
				.body(addproduct).when().post("/api/ecom/user/add-to-cart")
		.then().assertThat().statusCode(200).extract().response().as(AddToCart_Response.class);
		
		System.out.println(addResponse.getMessage());
		
	}
	
	@Test(priority=11, enabled=true)
	public void CreateOrder() {
		
		PlaceOrder_Secondpojo_Request pod = new PlaceOrder_Secondpojo_Request();
		pod.setCountry("India");
		pod.setProductOrderedId(productId);
		
		List<PlaceOrder_Secondpojo_Request> listOfOrders = new ArrayList<PlaceOrder_Secondpojo_Request>();
		listOfOrders.add(pod);
		
		PlaceOrder_Request order = new PlaceOrder_Request();
		order.setOrders(listOfOrders);
		
		PlaceOrder_Positive_Response getOrders=
				given().spec(req).header("Authorization", token)
				.body(order).when().post("/api/ecom/order/create-order")
		.then().log().all().assertThat().statusCode(201).extract().response().as(PlaceOrder_Positive_Response.class);
		
		System.out.println(getOrders.getMessage());
		List<String> allorders=getOrders.getOrders();
		for(int i=0; i<allorders.size();i++) {
			 orderId= allorders.get(i);
			System.out.println(orderId);
		}
						
	}
	
	@Test(priority=12, enabled=true)
	public void CreateOrderWithoutToken() {
		
		PlaceOrder_Secondpojo_Request pod = new PlaceOrder_Secondpojo_Request();
		pod.setCountry("India");
		pod.setProductOrderedId(productId);
		
		List<PlaceOrder_Secondpojo_Request> listOfOrders = new ArrayList<PlaceOrder_Secondpojo_Request>();
		listOfOrders.add(pod);
		
		PlaceOrder_Request order = new PlaceOrder_Request();
		order.setOrders(listOfOrders);
		
		InvalidToken_Response createOrderWithoutToken=
				given().spec(req).body(order).when().post("/api/ecom/order/create-order")
		.then().log().all().assertThat().statusCode(401).extract().response().as(InvalidToken_Response.class);
		
		System.out.println(createOrderWithoutToken.getMessage());
		
		}
	
	@Test(priority=13, enabled=true)
	public void CreateOrderInvalidToken() {
		
		PlaceOrder_Secondpojo_Request pod = new PlaceOrder_Secondpojo_Request();
		pod.setCountry("India");
		pod.setProductOrderedId(productId);
		
		List<PlaceOrder_Secondpojo_Request> listOfOrders = new ArrayList<PlaceOrder_Secondpojo_Request>();
		listOfOrders.add(pod);
		
		PlaceOrder_Request order = new PlaceOrder_Request();
		order.setOrders(listOfOrders);
		
		InvalidToken_Response createOrderInvalidToken=
				given().spec(req).body(order).header("Authorization","NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2IyYzIzOTAzODQxZTljOWE2NzczOGYiLCJ1c2VyRW1haWwiOiJzaHViaGFtc2hhcm1xYUBnbWFpLmNvbSIsInVzZXJNb2JpbGUiOjk5Nzc4ODMzNDQsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE2NzQ5MDAyMjEsImV4cCI6MTcwNjQ1NzgyMX0.7Qi0_CmmMg6lQXCMb9_J2Ee-lmG_AOFsMK6hilOyPv")
				.when().post("/api/ecom/order/create-order")
		.then().log().all().assertThat().statusCode(401).extract().response().as(InvalidToken_Response.class);
		
		System.out.println(createOrderInvalidToken.getMessage());
		
		}
	
	@Test(priority=14, enabled=true)
	public void CreateOrder_WithoutProductId() {
		
		PlaceOrder_Secondpojo_Request pdw = new PlaceOrder_Secondpojo_Request();
		pdw.setCountry("India");
		pdw.setProductOrderedId("");
		
		List<PlaceOrder_Secondpojo_Request> lists = new ArrayList<PlaceOrder_Secondpojo_Request>();
		lists.add(pdw);
		
		PlaceOrder_Request parentObj = new PlaceOrder_Request();
		parentObj.setOrders(lists);
		
		PlaceOrder_WithoutProductID_Response withoutId = given().spec(req).header("Authorization",token).body(parentObj).when().post("/api/ecom/order/create-order")
		.then().assertThat().statusCode(500).extract().response().as(PlaceOrder_WithoutProductID_Response.class);
		
		System.out.println(withoutId.getMessage().getMessage());
		
	}
	
	@Test(priority=15, enabled=true)
	public void CreateOrder_IncorrectProductId() {
		
		PlaceOrder_Secondpojo_Request psr = new PlaceOrder_Secondpojo_Request();
		psr.setCountry("India");
		psr.setProductOrderedId("63c132aa568c3e9fb1f6d1dasjghd");
		
		List<PlaceOrder_Secondpojo_Request> listofprod = new ArrayList<PlaceOrder_Secondpojo_Request>();
		listofprod.add(psr);
		
		PlaceOrder_Request iProdIdObj = new PlaceOrder_Request();
		iProdIdObj.setOrders(listofprod);
		
		PlaceOrder_WithoutProductID_Response incorrectId = given().spec(req).header("Authorization", token).body(iProdIdObj).when().post("/api/ecom/order/create-order")
		.then().assertThat().statusCode(500).extract().response().as(PlaceOrder_WithoutProductID_Response.class);
		
		System.out.println(incorrectId.getMessage().getMessage());
		
	}
	
	@Test(priority=16, enabled=true)
	public void orderDetails() {
		
		OrderDetails_Response allOrderDetails=given().spec(req).header("Authorization", token).queryParam("id", orderId)
				.when().get("/api/ecom/order/get-orders-details")
		.then().assertThat().statusCode(200).extract().response().as(OrderDetails_Response.class);
		
		System.out.println(allOrderDetails.getMessage());
		System.out.println(allOrderDetails.getData().getOrderBy());
		System.out.println(allOrderDetails.getData().getOrderById());
	}
	
	@Test(priority=17, enabled=true)
	public void orderDetailsWithoutToken() {
		
		InvalidToken_Response allOrderDetailsWithoutToken=given().spec(req).queryParam("id", orderId)
				.when().get("/api/ecom/order/get-orders-details")
		.then().assertThat().statusCode(401).extract().response().as(InvalidToken_Response.class);
		
		System.out.println(allOrderDetailsWithoutToken.getMessage());
	}
	
	@Test(priority=18, enabled=true)
	public void orderDetailsInvalidToken() {
		
		InvalidToken_Response allOrderDetailsInvalidToken=given().header("Authorization","NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2IyYzIzOTAzODQxZTljOWE2NzczOGYiLCJ1c2VyRW1haWwiOiJzaHViaGFtc2hhcm1xYUBnbWFpLmNvbSIsInVzZXJNb2JpbGUiOjk5Nzc4ODMzNDQsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE2NzQ5MDAyMjEsImV4cCI6MTcwNjQ1NzgyMX0.7Qi0_CmmMg6lQXCMb9_J2Ee-lmG_AOFsMK6hilOyPv")
				.spec(req).queryParam("id", orderId)
				.when().get("/api/ecom/order/get-orders-details")
		.then().assertThat().statusCode(401).extract().response().as(InvalidToken_Response.class);
		
		System.out.println(allOrderDetailsInvalidToken.getMessage());
	}
	
	@Test(priority=19, enabled=true)
	public void orderDetailsWithInvalidOrderId() {
		
		PlaceOrder_WithoutProductID_Response invalidOrder=given().spec(req).header("Authorization", token).queryParam("id", "63d270dc568c3e9fb102ce")
		.when().get("/api/ecom/order/get-orders-details").then().assertThat().extract()
		.response().as(PlaceOrder_WithoutProductID_Response.class);
		
		System.out.println(invalidOrder.getType());
		System.out.println(invalidOrder.getMessage().getMessage());
	}
	
	@Test(priority=20, enabled=true)
	public void orderDetailsWithoutOrderId() {
		
		PlaceOrder_WithoutProductID_Response missingOrderId=given().spec(req).header("Authorization", token).queryParam("id", "")
				.when().get("/api/ecom/order/get-orders-details").then().assertThat().extract()
				.response().as(PlaceOrder_WithoutProductID_Response.class);
				
				System.out.println(missingOrderId.getType());
				System.out.println(missingOrderId.getMessage().getMessage());
		
	}
	
	@Test(priority=21, enabled=true)
	public void deleteOrder() {
		
		DeleteOrder deleteOrder=given().spec(req).header("Authorization", token).pathParam("orderId", orderId).when()
		.delete("/api/ecom/order/delete-order/{orderId}").then().assertThat().statusCode(200).extract().response().as(DeleteOrder.class);
		
		System.out.println(deleteOrder.getMessage());
	}
	
	@Test(priority=22, enabled=true)
	public void deleteOrderWithoutToken() {
		
		InvalidToken_Response deleteOrderWithoutToken=given().spec(req).pathParam("orderId", orderId).when()
		.delete("/api/ecom/order/delete-order/{orderId}").then().assertThat().statusCode(401).extract()
		.response().as(InvalidToken_Response.class);
		
		System.out.println(deleteOrderWithoutToken.getMessage());
	}
	
	@Test(priority=23, enabled=true)
	public void deleteOrderInvalidToken() {
		
		InvalidToken_Response deleteOrderInvalidToken=given().spec(req).pathParam("orderId", orderId).when()
		.delete("/api/ecom/order/delete-order/{orderId}").then().assertThat().statusCode(401).extract()
		.response().as(InvalidToken_Response.class);
		
		System.out.println(deleteOrderInvalidToken.getMessage());
	}
	
	
	@Test(enabled=false)
	public void endToEnd() {
		
		//Login
		Login_Request lr = new Login_Request();
		lr.setUserEmail("shubhamsharmqa@gmai.com");
		lr.setUserPassword("Shubh@m8");
		
		
		Login_Response token=given().spec(req)
		.body(lr).when().post("/api/ecom/auth/login").then().assertThat().statusCode(200)
		.extract().response().as(Login_Response.class);
		
		String actualToken=token.getToken();
		System.out.println(actualToken);
		
		String id=token.getUserId();
		System.out.println(id);
		
		//Create new Product
		CreateProduct_Response productId=given().baseUri("https://rahulshettyacademy.com").header("Authorization",actualToken)
		.param("productName", "Apple_Laptop").param("productAddedBy", id).param("productCategory", "Electronics")
		.param("productSubCategory", "Laptops").param("productPrice", "115000").param("productDescription", "Apple_laptop")
		.param("productFor", "men")
		.multiPart("productImage", new File("C:\\Users\\shubhamsharma11\\OneDrive - Nagarro\\Desktop\\laptop.png"))
		.when().post("/api/ecom/product/add-product").then().assertThat().statusCode(201).extract().response()
		.as(CreateProduct_Response.class);
		
		String actualProductId=productId.getProductId();
		System.out.println(actualProductId);
		
		//Add to cart product
	/*	
		AddToCart_Request ad= new AddToCart_Request();
	
		AddToCart_Products_Request adp = new AddToCart_Products_Request();
		adp.set__v(0);
		adp.set_id(actualProductId);
		adp.setProductAddedBy(id);
		adp.setProductCategory("Electronics");
		adp.setProductDescription("Apple_laptop");
		adp.setProductFor("men");
		adp.setProductImage("C:\\Users\\shubhamsharma11\\eclipse-workspace\\RestAPI\\src\\Files\\Create_product_laptop.txt");
	*/	
		AddToCart_Response cart=given().spec(req).header("Authorization",actualToken).body(new File("C:\\Users\\shubhamsharma11\\eclipse-workspace\\RestAPI\\src\\Files\\AddToCart.txt"))
		.when().post("/api/ecom/user/add-to-cart").then().assertThat().statusCode(200)
		.extract().response().as(AddToCart_Response.class);
		
		System.out.println(cart.getMessage());
		
		//Place order
		
		PlaceOrder_Secondpojo_Request ps = new PlaceOrder_Secondpojo_Request();
		ps.setCountry("India");
		ps.setProductOrderedId(actualProductId);
		
		List<PlaceOrder_Secondpojo_Request> listOfOrders = new ArrayList<PlaceOrder_Secondpojo_Request>();
		listOfOrders.add(ps);
		
		PlaceOrder_Request pd = new PlaceOrder_Request();
		pd.setOrders(listOfOrders);
		
		String placeOrder = given().spec(req).header("Authorization",actualToken).body(pd)
		.when().post("/api/ecom/order/create-order").then()
		.assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(placeOrder);
		String orderId=js.getString("orders");
		System.out.println(orderId);
		
	// Delete Product
		String deleteProduct=given().spec(req).header("Authorization",actualToken).pathParam("actualProductId", actualProductId)
				.when().delete("/api/ecom/product/delete-product/{actualProductId}").then()
		.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jsdel = new JsonPath(deleteProduct);
		System.out.println(jsdel.getString("message"));
		
	}

}
