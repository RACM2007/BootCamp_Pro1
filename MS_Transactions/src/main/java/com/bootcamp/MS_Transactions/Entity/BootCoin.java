package com.bootcamp.MS_Transactions.Entity;

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
public class BootCoin {
	
	private double Currency_Sol_Change;
    private Date DateCreate
;
	
}
