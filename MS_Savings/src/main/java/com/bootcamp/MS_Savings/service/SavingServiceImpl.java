package com.bootcamp.MS_Savings.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.bootcamp.MS_Savings.Entity.SavingObj;
import com.bootcamp.MS_Savings.Entity.SumAmmount;
import com.bootcamp.MS_Savings.model.Accounts_Relationship;
import com.bootcamp.MS_Savings.model.Savings;
import com.bootcamp.MS_Savings.repository.SavingRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingServiceImpl implements SavingService{

	@Autowired
	private SavingRepository savingRepository;
	
	@Autowired
	private Accounts_RelationshipService accounts_RelationshipService;
	
	@Autowired
	private Debit_CardService debit_CardService;
		
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	private static Logger LogJava = Logger.getLogger(SavingServiceImpl.class);

	@Override
	public Flux<Savings> Savings_byCodcli(String codcli) {
		
		List<Accounts_Relationship> Far = accounts_RelationshipService.GetAccountSavList(codcli);
		
		List<Savings> LSav = new ArrayList<Savings>();
		
		Far.forEach(x -> LSav.add(GetSavingByProCurNum(x.getProduct(), x.getCurrency(), x.getNumber())));
		
		Mono<List<Savings>> MLSav = Mono.just(LSav);
		
		return MLSav.flatMapMany(Flux::fromIterable);
				
		
	}
	
	@Override
	public Mono<Savings> GetAccountOrden(String DebitCardNumber, double Amount) {
		
		//Find Codcli
		String codcli= debit_CardService.GetCodCli(DebitCardNumber);
				
		//Find Accounts
		List<Accounts_Relationship> FSav = accounts_RelationshipService.GetAccountSavList(codcli);
		
		for (Accounts_Relationship Savc:FSav) {
			if (Amount <= Inquiry(Savc.getProduct(), Savc.getCurrency(), Savc.getNumber()).block()) {
				return savingRepository.findAll().filter(x -> x.getProduct().equals(Savc.getProduct())
						&& x.getCurrency().equals(Savc.getCurrency())
						&& x.getNumber().equals(Savc.getNumber())).next();
			}
		}
		
		 return null;
	}
	
	public Savings GetSavingByProCurNum (String Product, String Currency, String Number){
		Savings Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(Product)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next().block();
		
		return Obj1;
	}
	
	@Override
	public Map<String, Object> SavReportByProduct(String pro) {
		LogJava.info("Generate Report by Product");
		
		//Savings Account List
		Map<String, Object> response = new HashMap<String, Object>();
		
		Flux<Savings> PagSav = savingRepository.findAll().filter(x -> x.getProduct().equals(pro));
		
		SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC,"DateCreate"));
		MatchOperation comparisonOperatorsA = Aggregation.match(Criteria.where("Product").is(pro));
		Aggregation aggregationAll = Aggregation.newAggregation(sortOperation,comparisonOperatorsA);
		
		AggregationResults<Savings> resultsAll =
				mongoTemplate.aggregate(aggregationAll,"Savings", Savings.class);
		
		//Savings List
		response.put("Savings_Account_List", resultsAll.getMappedResults());
		
		//Total Elements
		response.put("Total_Savings_Account", PagSav.count().block());
		
		//Ammount Sum
		GroupOperation groupOperation = Aggregation.group().sum("Amount").as("totalAmount");
		MatchOperation comparisonOperators = Aggregation.match(Criteria.where("FlgActive").is(1));

		TypedAggregation<Savings> aggregation = Aggregation.newAggregation(
				Savings.class,
				comparisonOperators,
				comparisonOperatorsA,
				groupOperation
		);

		AggregationResults<SumAmmount> results =
				mongoTemplate.aggregate(aggregation, SumAmmount.class);
		
		response.put("Total_Sum_Amount", results.getMappedResults());
		
		//Ammount Average
		GroupOperation groupOperation2 = Aggregation.group().avg("Amount").as("totalAmount");
		MatchOperation comparisonOperators2 = Aggregation.match(Criteria.where("FlgActive").is(1));

		TypedAggregation<Savings> aggregation2 = Aggregation.newAggregation(
				Savings.class,
				comparisonOperators2,
				comparisonOperatorsA,
				groupOperation2
		);

		AggregationResults<SumAmmount> results2 =
				mongoTemplate.aggregate(aggregation2, SumAmmount.class);
		
		response.put("Average_Amount", results2.getMappedResults());
		
		
		//Rate Average
		GroupOperation groupOperation3 = Aggregation.group().avg("Rate").as("totalAmount");

		TypedAggregation<Savings> aggregation3 = Aggregation.newAggregation(
				Savings.class,
				groupOperation3
		);

		AggregationResults<SumAmmount> results3 =
				mongoTemplate.aggregate(aggregation3, SumAmmount.class);
		
		response.put("Average_Rate", results3.getMappedResults());
		
		
		//Last Saving
		response.put("Saving_Last", resultsAll.getMappedResults().get(resultsAll.getMappedResults().size()-1));
		

		
		return response;
	}
	
	@Override
	public Map<String,Object> SavReport() {
		LogJava.info("Generate Report");
		
		//Savings Account List
		Map<String, Object> response = new HashMap<String, Object>();
		
		
		Flux<Savings> PagSav = savingRepository.findAll();
		
		SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC,"DateCreate"));
		Aggregation aggregationAll = Aggregation.newAggregation(sortOperation);
		
		AggregationResults<Savings> resultsAll =
				mongoTemplate.aggregate(aggregationAll,"Savings", Savings.class);
		
		//Savings List
		response.put("Savings_Account_List", resultsAll.getMappedResults());
		
		//Total Elements
		response.put("Total_Savings_Account", PagSav.count().block());
		
		//Ammount Sum
		GroupOperation groupOperation = Aggregation.group().sum("Amount").as("totalAmount");
		MatchOperation comparisonOperators = Aggregation.match(Criteria.where("FlgActive").is(1));

		TypedAggregation<Savings> aggregation = Aggregation.newAggregation(
				Savings.class,
				comparisonOperators,
				groupOperation
		);

		AggregationResults<SumAmmount> results =
				mongoTemplate.aggregate(aggregation, SumAmmount.class);
		
		response.put("Total_Sum_Amount", results.getMappedResults());
		
		//Ammount Average
		GroupOperation groupOperation2 = Aggregation.group().avg("Amount").as("totalAmount");
		MatchOperation comparisonOperators2 = Aggregation.match(Criteria.where("FlgActive").is(1));

		TypedAggregation<Savings> aggregation2 = Aggregation.newAggregation(
				Savings.class,
				comparisonOperators2,
				groupOperation2
		);

		AggregationResults<SumAmmount> results2 =
				mongoTemplate.aggregate(aggregation2, SumAmmount.class);
		
		response.put("Average_Amount", results2.getMappedResults());
		
		
		//Rate Average
		GroupOperation groupOperation3 = Aggregation.group().avg("Rate").as("totalAmount");

		TypedAggregation<Savings> aggregation3 = Aggregation.newAggregation(
				Savings.class,
				groupOperation3
		);

		AggregationResults<SumAmmount> results3 =
				mongoTemplate.aggregate(aggregation3, SumAmmount.class);
		
		response.put("Average_Rate", results3.getMappedResults());
		
		
		//Last Saving
		response.put("Saving_Last", resultsAll.getMappedResults().get(resultsAll.getMappedResults().size()-1));
		
		return response;
	}
	
	@Override
	public Flux<Savings> AllSavings() {
		LogJava.info("List all Savings Account");
		return savingRepository.findAll();
	}


	@Override
	public Mono<Double> Inquiry(String pro, String Currency, String Number) {
		LogJava.info("Inquiry");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		return Obj1.map( r -> r.getAmount());
		
	}
	
	@Override
	public Mono<Double> InquiryDebitCard(String Number) {
		
		SavingObj SO = debit_CardService.GetPrinSav(Number);
		
		return Inquiry(SO.getProduct(), SO.getCurrency(), SO.getNumber());
		
		
	}
	
	@Override
	public Mono<Integer> GetTypeAccount(String pro, String Currency, String Number) {
		LogJava.info("Inquiry");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		return Obj1.map( r -> r.getType());
		
	}

	@Override
	public Mono<Savings> AmountUpdate(String pro, String Currency, String Number,double NewAmou) throws Exception {
		LogJava.info("AmountUpdate");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
	
		Savings Sav = new Savings();
		
		if (Obj1 != null) {
			Sav = Obj1.block();
			
			Sav.setAmount(NewAmou);
		}
		
		//throw new  Exception("asd");
		
		return savingRepository.save(Sav);
	}


	@Override
	public Mono<Savings> Open(SavingObj Sav) {
		LogJava.info("Open Saving");
		
		boolean flg1;
		
		//business condition
		
		if (Sav.getTypeCLi()==1) {
		
			flg1 = PerCond(Sav.getCodTit());
		}else {
			flg1 = BusiCond(Sav.getCodTit());
		}
		
		Savings SavR = Sav.GetSaving();
		
		
		if (flg1) {
			Flux.fromIterable(Sav.GetRelation()).flatMap(R -> 
			Flux.concat(accounts_RelationshipService.save(R))).subscribe();
			
			return savingRepository.save(SavR);
		}else {
			return null;
		}
	}

	private boolean BusiCond(List<String> codTit) {
		
		for(String cc: codTit) {
			//Accounts List
			Flux<Accounts_Relationship> far = accounts_RelationshipService.GetAccountSav(cc);
						
			boolean Bol = far.map(f -> GetTypeAccount(f.getProduct(),f.getCurrency(),f.getNumber()))
				.filter(x -> x.block()==1 || x.block()==2).collect(Collectors.toSet()).block().size()==2;
			
			if (Bol) {
				return false;
			}
		}
		
		return true;
		
		
	}
	
	private boolean PerCond(List<String> codTit) {
		return accounts_RelationshipService.PerCond(codTit);
	}

	@Override
	public Mono<Savings> Close(String pro, String Currency, String Number) {
		LogJava.info("Close Saving");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		
		Savings Sav = Obj1.block();
		
		Sav.setFlgActive(0);
		
		return savingRepository.save(Sav);
	}


	@Override
	public Mono<Void> Delete(String pro, String Currency, String Number) {
		LogJava.info("Delete Saving");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		
		Savings Sav = Obj1.block();
		
		return savingRepository.delete(Sav);
	}

	
			
}
