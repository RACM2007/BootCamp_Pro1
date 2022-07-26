package com.bootcamp.MS_BootCoin.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Clients {

    private String CodClient;
    private String Name;
    private String Address;
    private String Document;
    private int FlgActive;
    private int Type;

}
