package com.MS_Yanki.MS_Yanki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MS_Yanki.MS_Yanki.Entity.Clients;
import com.MS_Yanki.MS_Yanki.Entity.NoClients;
import com.MS_Yanki.MS_Yanki.Entity.UserBC;
import com.MS_Yanki.MS_Yanki.service.YankiService;


@RestController
@RequestMapping(value="/yanki")
@CrossOrigin("*")
public class YankiController {

	@Autowired
    YankiService yankiService;
	
	@PostMapping(value = "/RegistryUser/{isClient}")
    public String Registry_User(@PathVariable("isClient") int isClient, @RequestBody UserBC uBC) {
        //Registry User
		
		if (isClient==1) {
			Clients cli = uBC.toClient();
			return yankiService.Registry_User_Cli(cli);
			
		}else {
			NoClients Ncli = uBC.toNoClient();
			return yankiService.Registry_User_NoCli(Ncli);
		}
		
    }
	
}
