package com.amcom.salesprocessor.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amcom.salesprocessor.parser.Customer;
import com.amcom.salesprocessor.parser.Sale;
import com.amcom.salesprocessor.parser.SalesMan;

public class FileUtils {
	
	public FileUtils() {}
	
	private List<SalesMan> salesMans = new ArrayList<SalesMan>();
	private List<Customer> customers = new ArrayList<Customer>();
	private List<Sale> sales = new ArrayList<Sale>();
	
	public Integer getCustpomerAmount() {
		return getAmount(customers.stream().map(Customer::getCnpj).collect(Collectors.toList()));
	}
	
	public Integer getSalesManAmount() {
		return getAmount(salesMans.stream().map(SalesMan::getCpf).collect(Collectors.toList()));
	}
	
	public Integer getIdBiggerSale() {
		Sale biggerSale = null;
		for (Sale sale : sales) {
			if (biggerSale == null || sale.getTotalValue() >= biggerSale.getTotalValue()) {
				biggerSale = sale;
			}
		}
		return biggerSale.getId();
	}
	
	public String getWorseSalesMan() {
		Map<String, Double> salesManSales = new HashMap<String, Double>();
		for (Sale sale : sales) {
			if (salesManSales.containsKey(sale.getSalesMan())) {
				salesManSales.replace(sale.getSalesMan(), (salesManSales.get(sale.getSalesMan())+sale.getTotalValue()));
			} else {
				salesManSales.put(sale.getSalesMan(), sale.getTotalValue());
			}
		}
		Map.Entry<String,Double> worseSalesMan = null;
		for(Map.Entry<String,Double> entry : salesManSales.entrySet()) {
			if (worseSalesMan == null || worseSalesMan.getValue() >= entry.getValue()) {
				worseSalesMan = entry;
			}
		}
		return worseSalesMan.getKey();
	}
	
	private Integer getAmount(List<String> list) {
		List<String> aux = new ArrayList<String>();
		for (String s : list) {
			if (!aux.contains(s)) {
				aux.add(s);
			}
		}
		return aux.size();
	} 
	
	public List<SalesMan> getSalesMan() {
		return salesMans;
	}
	public void setSalesMan(List<SalesMan> salesMans) {
		this.salesMans = salesMans;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public List<Sale> getSales() {
		return sales;
	}
	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}
	
}
