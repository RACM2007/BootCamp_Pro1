package com.bootcamp.MS_Credits.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "Credits")
public class Credits implements Serializable {

    @Id
    private ObjectId id;

    private String Product;
    private String Currency;
    private String Number;
    private String CodClient;
    private Double Amount;
    private Double Balance;
    private Double Rate;
    private int FlgActive;
    private Date DateCreate;
    private int Type;

}
