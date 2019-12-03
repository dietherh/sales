package com.amcom.salesprocessor.parser;

public class SalesMan {
	
	public SalesMan() {}
	
	public SalesMan(String cpf, String name, Double salary) {
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}
	
	private String cpf;
	private String name;
	private Double salary;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setNome(String name) {
		this.name = name;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
