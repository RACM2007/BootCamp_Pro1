package com.MS_Yanki.MS_Yanki.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class Clients {

    private String CodClient;
    private String Name;
    private String Address;
    private String Document;
    private int FlgActive;
    private int Type;

}
