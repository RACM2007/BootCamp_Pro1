package com.MS_Yanki.MS_Yanki.service;

import com.MS_Yanki.MS_Yanki.Entity.Clients;
import com.MS_Yanki.MS_Yanki.Entity.NoClients;

public interface YankiService {

	String Registry_User_Cli(Clients cli);
	
	String Registry_User_NoCli(NoClients Ncli);
	
}
