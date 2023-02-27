package Ecommerce_Pojo;

public class OrderDetails_Response {
	
	private OrderDetails_Data_Response data;
	public OrderDetails_Data_Response getData() {
		return data;
	}
	public void setData(OrderDetails_Data_Response data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;

}
