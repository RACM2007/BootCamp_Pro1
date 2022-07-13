package com.MS_Commissions.MS_Commissions.service;

import com.MS_Commissions.MS_Commissions.Entity.GenOpeAmmount;
import com.MS_Commissions.MS_Commissions.model.Commissions;
import com.MS_Commissions.MS_Commissions.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;
    
    @Autowired
	MongoTemplate mongoTemplate;

    private static Logger LogJava = Logger.getLogger(CommissionServiceImpl.class);

    @Override
    public Flux<Commissions> findAll() {
    	//List Commissions
        LogJava.info("List all Commissions");
        return commissionRepository.findAll();
    }

    @Override
    public Mono<Commissions> save(Commissions commissions) {
    	//Register Commission
        LogJava.info("Save");
        return commissionRepository.save(commissions);
    }

    @Override
    public Map<String,Object> getReport() {
        
    	LogJava.info("Generate Report");
		
		//Commissions Account List
		Map<String, Object> response = new HashMap<String, Object>();
		
		Flux<Commissions> PagCom = commissionRepository.findAll();
		
		SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC,"Codcli"));
		Aggregation aggregationAll = Aggregation.newAggregation(sortOperation);
		
		AggregationResults<Commissions> resultsAll =
				mongoTemplate.aggregate(aggregationAll,"Commissions", Commissions.class);
		
		//Ammount Average
		GroupOperation groupOperation2 = Aggregation.group().avg("Amount").as("Gen_Ope_Amount");
		
		TypedAggregation<Commissions> aggregation2 = Aggregation.newAggregation(
				Commissions.class,
				groupOperation2
		);
		
		AggregationResults<GenOpeAmmount> results2 =
				mongoTemplate.aggregate(aggregation2, GenOpeAmmount.class);
		
		response.put("Average_Amount", results2.getMappedResults());
		
		//Total Elements
		response.put("Total_Commissions_Account", resultsAll.getMappedResults().size());
		
		//Last Commissions
		response.put("Commissions_Last", resultsAll.getMappedResults().get(resultsAll.getMappedResults().size()-1));
		
		//Commissions List
		response.put("Commissions_Account_List", resultsAll.getMappedResults());
		
		return response;
    	
    }
}
