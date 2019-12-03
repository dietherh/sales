package com.amcom.salesprocessor.parser;

public class Customer {
	
	public Customer() {}
	
	public Customer(String cnpj, String name, String area) {
		this.cnpj = cnpj;
		this.name = name;
		this.area = area;
	}
	
	private String cnpj;
	private String name;
	private String area;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getName() {
		return name;
	}
	public void setNome(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
	
}
