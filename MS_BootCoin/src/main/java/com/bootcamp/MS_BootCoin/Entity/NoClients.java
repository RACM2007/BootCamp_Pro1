package com.bootcamp.MS_BootCoin.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class NoClients {
    private String CodNoClient;
    private String NameNt;
    private String Document;
    private String Number;
    private String CellImei;
    private String Mail;

}
