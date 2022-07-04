package com.bootcamp.MS_Savings.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


import org.bson.types.ObjectId;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "Savings")
public class Savings  implements Serializable  {

	@Id
	private ObjectId id;
	
	private String Product;
	private String Currency;
	private String Number;
	private double Amount;
	private double Rate;
	private int FlgActive;
	private Date DateCreate;
	private int Type;
	
	
}
