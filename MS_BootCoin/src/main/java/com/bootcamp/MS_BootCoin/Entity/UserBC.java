package com.bootcamp.MS_BootCoin.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserBC {

	private String CodClient;
    private String Name;
    private String Address;
    private String Document;
    
    private int FlgActive;
    private int Type;
    
    private String Number;
    private String CellImei;
    private String Mail;

    
    public Clients toClient() {
    	Clients c = new Clients();
    	
    	c.setCodClient(CodClient);
    	c.setAddress(Address);
    	c.setDocument(Document);
    	c.setFlgActive(FlgActive);
    	c.setName(Name);
    	c.setType(Type);
    	
    	return c;
    	
    }
    
    public NoClients toNoClient() {
    	NoClients c = new NoClients();
    	
    	c.setCodNoClient(CodClient);
    	c.setCellImei(CellImei);
    	c.setNumber(Number);
    	c.setMail(Mail);
    	c.setNameNt(Name);
    	c.setDocument(Document);
    	
    	return c;
    	
    }
    
}
