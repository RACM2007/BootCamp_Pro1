package com.MS_Commissions.MS_Commissions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Commissions")
public class Commissions implements Serializable {

    @Id
    private ObjectId id;

    private String Codcli;
    private int Trans_Number;
    private Double Amount;
    private int Type;
    private int Type_Pro;

}
