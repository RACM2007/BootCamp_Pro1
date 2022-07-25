package com.bootcamp.MS_Clients.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CheckUser {

	private String codClient;
	private String codNoClient;
	private boolean flgCli; 
	
}
