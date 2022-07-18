package com.bootcamp.MS_Transactions.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "Transaction")
public class Transactions  implements Serializable  {

	@Id
	private ObjectId id;
	
	private String Trans_Number;
	private int Type;
	private String Product;
	private String Currency;
	private String Number;
	private String CodClient;
	private double Amount;
	private Date DateCreate;
	
	private String ProductD;
	private String CurrencyD;
	private String NumberD;
	
	private String CardNumber;

	
	private String CodEntity;
	
}
