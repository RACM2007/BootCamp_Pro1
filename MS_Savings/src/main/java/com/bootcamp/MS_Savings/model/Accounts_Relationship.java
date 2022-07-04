package com.bootcamp.MS_Savings.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "Accounts_Relationship")
public class Accounts_Relationship   implements Serializable  {

	@Id
	private ObjectId id;
	
	private String Product;
	private String Currency;
	private String Number;
	private String CodClient;
	private int RelCod;
	
}
