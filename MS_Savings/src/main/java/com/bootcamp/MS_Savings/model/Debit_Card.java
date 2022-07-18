package com.bootcamp.MS_Savings.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "Debit_Card")
public class Debit_Card {

	@Id
	private ObjectId id;
	
	private String Codcli;
	private String Num_Card;
	private String Product_Prin;
	private String Currency_prin;
	private String Number_prin;
	
	
}
