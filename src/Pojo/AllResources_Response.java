package Pojo;

import java.util.List;

public class AllResources_Response {
	
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPer_page() {
		return per_page;
	}
	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}
	public List<AllResources_Data_Response> getData() {
		return data;
	}
	public void setData(List<AllResources_Data_Response> data) {
		this.data = data;
	}
	public AllResources_Support_Response getSupport() {
		return support;
	}
	public void setSupport(AllResources_Support_Response support) {
		this.support = support;
	}
	private int per_page;
	private int total;
	private int total_pages;
	private List<AllResources_Data_Response> data;
	private  AllResources_Support_Response support;

}
