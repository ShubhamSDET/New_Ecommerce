package Ecommerce_Pojo;

import java.util.List;

public class PlaceOrder_Request {
	
	private List<PlaceOrder_Secondpojo_Request> orders;

	public List<PlaceOrder_Secondpojo_Request> getOrders() {
		return orders;
	}

	public void setOrders(List<PlaceOrder_Secondpojo_Request> orders) {
		this.orders = orders;
	}

}
