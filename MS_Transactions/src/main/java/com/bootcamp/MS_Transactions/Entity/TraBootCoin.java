package com.bootcamp.MS_Transactions.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TraBootCoin {

	private int TypeTra;
	
	private double Amount;
	private String CodClient;
	private Date DateCreate;
	private String Number;
	private String Product;
	
}
