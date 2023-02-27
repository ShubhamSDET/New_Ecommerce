package Ecommerce_Pojo;

public class PlaceOrder_WithoutProductId_Message_Response {
	
	private String stringValue;
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String valueType;
	private String kind;
	private String value;
	private String path;
	private	PlaceOrder_WithoutProductId_Reason_Response reason;
	
	public PlaceOrder_WithoutProductId_Reason_Response getReason() {
		return reason;
	}
	public void setReason(PlaceOrder_WithoutProductId_Reason_Response reason) {
		this.reason = reason;
	}
	private String name;
	private String message;

}
