package Ecommerce_Pojo;

public class CreateProductWithoutImage_Second_Response {
	
	private CreateProductWithoutImage_Third_Response errors;
	public CreateProductWithoutImage_Third_Response getErrors() {
		return errors;
	}
	public void setErrors(CreateProductWithoutImage_Third_Response errors) {
		this.errors = errors;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String get_message() {
		return _message;
	}
	public void set_message(String _message) {
		this._message = _message;
	}
	private String message;
	private String name;
	private String _message;

}
