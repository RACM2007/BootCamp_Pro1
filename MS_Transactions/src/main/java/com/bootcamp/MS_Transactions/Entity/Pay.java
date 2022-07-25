package com.bootcamp.MS_Transactions.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Pay {

	private String Trans_Number;
	
	private String CodClient;
	private String CodNoClient;
	private double Amount;
	private String Phone_Number;
	
	private int Type;
	
}
