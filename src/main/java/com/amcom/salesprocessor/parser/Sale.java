package com.amcom.salesprocessor.parser;

import java.util.ArrayList;
import java.util.List;

public class Sale {
	
	public Sale() {}
	
	public Sale(Integer id, String salesman) {
		this.id = id;
		this.salesman = salesman;
		this.items = new ArrayList<SalesItem>();
	}
	
	private Integer id;
	private String salesman;
	private List<SalesItem> items = new ArrayList<SalesItem>();
	
	public void add(SalesItem salesItemDTO) {
		this.items.add(salesItemDTO);
	}
	
	public Double getTotalValue() {
		return items.stream().mapToDouble(item -> item.getPrice()).sum();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSalesMan() {
		return salesman;
	}
	public void setSalesMan(String salesman) {
		this.salesman = salesman;
	}
	public List<SalesItem> getItens() {
		return items;
	}
	public void setItens(List<SalesItem> items) {
		this.items = items;
	}
	
}
