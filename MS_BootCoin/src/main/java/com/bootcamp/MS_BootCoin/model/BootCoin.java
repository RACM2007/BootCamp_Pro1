package com.bootcamp.MS_BootCoin.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BootCoin")
public class BootCoin implements Serializable {

	@Id
    private ObjectId id;
	
	private double Currency_Sol_Change;
    private Date DateCreate;
	
}
