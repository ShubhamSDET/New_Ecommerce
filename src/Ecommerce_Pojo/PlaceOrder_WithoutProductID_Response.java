package Ecommerce_Pojo;

public class PlaceOrder_WithoutProductID_Response {
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public PlaceOrder_WithoutProductId_Message_Response getMessage() {
		return message;
	}
	public void setMessage(PlaceOrder_WithoutProductId_Message_Response message) {
		this.message = message;
	}
	private PlaceOrder_WithoutProductId_Message_Response message;

}
