package com.bootcamp.MS_Transactions;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import com.bootcamp.MS_Transactions.model.Transactions;
import com.bootcamp.MS_Transactions.repository.TransactionRepository;
import com.bootcamp.MS_Transactions.service.TransactionService;

import reactor.core.publisher.Mono;

@SpringBootTest
class MsTransactionsApplicationTests {

	@Autowired
	private TransactionService transactionService;
	
	@MockBean
	private TransactionRepository transactionRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	void TestOne() {
		System.out.println("Starting Test Deposits");
		
		Transactions Tra = new Transactions();
		
		Tra.setAmount(5.0);
		Tra.setCodClient("111");
		Tra.setCurrency("0");
		Tra.setDateCreate(new Date());
		Tra.setNumber("9");
		Tra.setProduct("9");
		
		Mockito.when(transactionRepository.save(any(Transactions.class))).thenReturn(Mono.just(Tra));
		
		transactionService.Deposits(null, null, null, null, 0).subscribe( x -> {
			Assertions.assertNotNull(x);
		} );
		
	}
	
}
