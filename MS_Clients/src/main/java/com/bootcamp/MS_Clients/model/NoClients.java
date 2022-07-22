package com.bootcamp.MS_Clients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "NoClients")
public class NoClients implements Serializable {

    @Id
    private ObjectId id;

    private String CodNoClient;
    private String NameNt;
    private String Document;
    private String Number;
    private String CellImei;
    private String Mail;

}
