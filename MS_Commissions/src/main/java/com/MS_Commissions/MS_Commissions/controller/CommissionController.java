package com.MS_Commissions.MS_Commissions.controller;

import com.MS_Commissions.MS_Commissions.model.Commissions;
import com.MS_Commissions.MS_Commissions.service.CommissionService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/commission")
@CrossOrigin("*")
public class CommissionController {

    @Autowired
    CommissionService commissionService;

    @GetMapping(value = "/AllCommissions")
    public Flux<Commissions> AllCommissions() {
        //List all Commissions
        return commissionService.findAll();
    }
    
    @GetMapping(value = "/Get_Report")
	public Map<String,Object> GetReport() {
		//Get a Resume about the commission
		return commissionService.getReport();
	}

    @PostMapping(value = "/NewCommission")
    public Mono<Commissions> save(@RequestBody Commissions commissions){
        //Register new commission
        return commissionService.save(commissions);
    }

}
