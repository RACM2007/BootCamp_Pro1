package com.MS_Yanki.MS_Yanki.service;

import com.MS_Yanki.MS_Yanki.Entity.CheckUser;
import com.MS_Yanki.MS_Yanki.Entity.Clients;
import com.MS_Yanki.MS_Yanki.Entity.NoClients;
import com.MS_Yanki.MS_Yanki.Entity.Pay;

import reactor.core.publisher.Mono;

public interface YankiService {

	String Registry_User_Cli(Clients cli);
	
	String Registry_User_NoCli(NoClients Ncli);

	String Send_Pay(Pay payO);

	String Rece_Pay(Pay payO);
	
}
