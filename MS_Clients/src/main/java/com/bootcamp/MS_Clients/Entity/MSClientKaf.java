package com.bootcamp.MS_Clients.Entity;


import com.bootcamp.MS_Clients.model.Clients;
import com.bootcamp.MS_Clients.model.NoClients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class MSClientKaf {

	boolean isclient;
	
	private Clients cli;
	private NoClients noCli;
    
	
}
