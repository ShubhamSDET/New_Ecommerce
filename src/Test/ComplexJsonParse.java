package Test;

import org.testng.Assert;

import Files.AddPlacePayload;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js=new JsonPath(AddPlacePayload.CoursePrice());
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		String purchase=js.getString("dashboard.purchaseAmount");
		System.out.println(purchase);
		
		String firsttitle=js.get("courses[0].title");
		System.out.println(firsttitle);
		
		for(int i=0;i<count;i++) {
			String alltitles=js.get("courses["+i+"].title");
			System.out.println(alltitles);
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		System.out.println("RPA price is down");
		for(int i=0;i<count;i++) {
			String alltitles=js.get("courses["+i+"].title");
			if(alltitles.equalsIgnoreCase("RPA")) {
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
			
		}
		int sum=0;
		for(int i=0;i<count;i++) {
			int p=js.get("courses["+i+"].price");
			int c=js.get("courses["+i+"].copies");
			int m = p*c;
				sum = sum + m;
		}
		System.out.println(sum);
		int purchaseamount=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseamount);
		
	}
  
}
