package com.bootcamp.MS_Transactions.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Transfer {

	private String Trans_Number;
	private int Type;
	private String Product;
	private String Currency;
	private String Number;
	private double Amount;
	
	private String ProductD;
	private String CurrencyD;
	private String NumberD;
	
	private String CodEntity;
	
	
}
