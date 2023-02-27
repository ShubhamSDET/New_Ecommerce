package Ecommerce_Pojo;

public class AddToCart_Request {
	
	private String _id;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public AddToCart_Products_Request getProduct() {
		return product;
	}
	public void setProduct(AddToCart_Products_Request product) {
		this.product = product;
	}
	private AddToCart_Products_Request product;

}
