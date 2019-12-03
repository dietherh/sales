package com.amcom.salesprocessor.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.amcom.salesprocessor.parser.Customer;
import com.amcom.salesprocessor.parser.Sale;
import com.amcom.salesprocessor.parser.SalesItem;
import com.amcom.salesprocessor.parser.SalesMan;
import com.amcom.salesprocessor.utils.FileUtils;
import com.amcom.salesprocessor.utils.PathUtils;


@Service
public class ProcessFileService {
	
	public ProcessFileService(){}
	
	public void processFile(File file) {
		if (file.getName().indexOf(".dat") != -1)  {
			FileUtils fileUtils = new FileUtils();
			try {
				String path = PathUtils.getInputDir()+file.getName();
				BufferedReader reader = 
					new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
				String line = reader.readLine();
				while (line != null) {
					processLine(line, fileUtils);
					line = reader.readLine();
				}
				reader.close();
				generateReturFile(file.getName(), fileUtils);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void generateReturFile(String nomeArquivo, FileUtils arquivo) {
		try {
			String path = PathUtils.getOutputDir() + nomeArquivo.subSequence(0, nomeArquivo.length()-4) + ".done.dat";
			FileWriter x = new FileWriter(path,false);
			x.write("quantidadeClientes: "+arquivo.getCustpomerAmount()+"\r\n");
			x.write("quantidadeVendedores: "+arquivo.getSalesManAmount()+"\r\n");
			x.write("idMaiorVenda: "+arquivo.getIdBiggerSale()+"\r\n");
			x.write("piorVendedor: "+arquivo.getWorseSalesMan()+"\r\n");
			x.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void processLine(String line, FileUtils processFile) {
		String type = line.substring(0,3);
		line = line.substring(4);
		String[] data = line.split("ç");
		
		if ("001".equals(type)) {
			processFile.getSalesMan().add(new SalesMan(data[0], data[1], Double.parseDouble(data[2])));
		}
		if ("002".equals(type)) {
			processFile.getCustomers().add(new Customer(data[0], data[1], data[2]));
		}
		if ("003".equals(type)) {
			Sale sale = new Sale(Integer.parseInt(data[0]), data[2]);
			String itens = data[1];
			itens = itens.substring(1, itens.length()-1);
			data = itens.split(",");
			for (int i = 0; i < data.length; i++) {  
	            String item[] = data[i].split("-");  
	            sale.add(new SalesItem(Integer.parseInt(item[0]), Integer.parseInt(item[1]), Double.parseDouble(item[2])));
	        }
			
			processFile.getSales().add(sale);
		}
	}
}
