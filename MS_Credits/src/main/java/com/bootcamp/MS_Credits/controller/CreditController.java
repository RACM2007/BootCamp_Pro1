package com.bootcamp.MS_Credits.controller;

import com.bootcamp.MS_Credits.model.Credits;
import com.bootcamp.MS_Credits.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/credit")
@CrossOrigin("*")
public class CreditController {

    @Autowired
    CreditService creditService;

    @GetMapping(value = "/AllCredits")
    public Flux<Credits> AllCredits() {
        return creditService.findAll();
    }

    @PostMapping(value = "/NewCredit")
    public Mono<Credits> save(@RequestBody Credits credits){
        return creditService.save(credits);
    }

    @GetMapping(value = "/Inquiry/{pro}/{cur}/{num}")
    public Mono<Double> Inquiry(@PathVariable("pro") String pro, @PathVariable("cur") String cur, @PathVariable("num") String num) {
        //Balance inquiry
        return creditService.Inquiry(pro, cur, num);
    }

    @PutMapping(value = "/UpdateBalance/{pro}/{cur}/{num}/{newamou}")
    public Mono<Credits> UpdateBalance(@PathVariable("pro")String pro,
                                      @PathVariable("cur")String Currency,
                                      @PathVariable("num") String Number,
                                      @PathVariable("newamou") double NewAmou){
        return creditService.updateBalance(pro, Currency, Number, NewAmou);
    }

    @DeleteMapping(value="/Delete/{pro}/{cur}/{num}")
    public Mono<Void> Delete(@PathVariable("pro") String pro, @PathVariable("cur") String cur, @PathVariable("num") String num){
        return creditService.delete(pro, cur, num);
    }

}
