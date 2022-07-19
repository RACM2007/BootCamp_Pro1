package com.bootcamp.MS_Credits.controller;

import com.bootcamp.MS_Credits.model.Credits;
import com.bootcamp.MS_Credits.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping(value="/credit")
@CrossOrigin("*")
public class CreditController {

    @Autowired
    CreditService creditService;

    @GetMapping(value = "/AllCredits")
    public Flux<Credits> AllCredits() {
        //List all credits
        return creditService.findAll();
    }

    @PostMapping(value = "/NewCredit")
    public Mono<Credits> save(@RequestBody Credits credits){
        //Register new credit
        return creditService.save(credits);
    }

    @GetMapping(value = "/Inquiry/{pro}/{cur}/{num}")
    public Mono<Double> Inquiry(@PathVariable("pro") String pro, @PathVariable("cur") String cur, @PathVariable("num") String num) {
        //Balance inquiry
        return creditService.Inquiry(pro, cur, num);
    }

    @GetMapping(value = "/GetCreditsbyCodCli/{cod}")
    public Mono<Credits> GetCreditsbyCodCli(@PathVariable("cod") String cod) {
        //Get Credit by CodCli
        return creditService.getCreditbyCodCli(cod);
    }

    @GetMapping(value = "/GetReport")
    public Mono<Credits> GetReport() {
        //Get Report Credit
        return creditService.getReport();
    }

    @GetMapping(value = "/GetReportbyProduct/{cod}")
    public Map<String,Object> GetReportbyProduct(@PathVariable("cod") String cod) {
        //Get Credit by CodCli
        return creditService.getReportbyProduct(cod);
    }

    @GetMapping(value = "/Get_debt/{cod}")
    public Mono<Credits> Get_debt(@PathVariable("cod") String cod) {
        //Get Report Debt
        return creditService.getDebt(cod);
    }

    @PutMapping(value = "/UpdateBalance/{pro}/{cur}/{num}/{newamou}")
    public Mono<Credits> UpdateBalance(@PathVariable("pro")String pro,
                                      @PathVariable("cur")String Currency,
                                      @PathVariable("num") String Number,
                                      @PathVariable("newamou") double NewAmou){
        //Update Balance
        return creditService.updateBalance(pro, Currency, Number, NewAmou);
    }

    @DeleteMapping(value="/Delete/{pro}/{cur}/{num}")
    public Mono<Void> Delete(@PathVariable("pro") String pro, @PathVariable("cur") String cur, @PathVariable("num") String num){
        //Delete Credit
        return creditService.delete(pro, cur, num);
    }

}
