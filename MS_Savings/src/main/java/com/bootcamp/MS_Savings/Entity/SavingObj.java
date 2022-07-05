package com.bootcamp.MS_Savings.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bootcamp.MS_Savings.model.Accounts_Relationship;
import com.bootcamp.MS_Savings.model.Savings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SavingObj {

	private String Product;
	private String Currency;
	private String Number;
	private double Amount;
	private double Rate;
	private int FlgActive;
	private Date DateCreate;
	private int Type;
	
	List<String> CodTit;
	List<String> CodSig;
	
	private int typeCLi;
	
	public Savings GetSaving() {
		Savings Sav = new Savings();
		
		Sav.setAmount(this.Amount);
		Sav.setCurrency(this.Currency);
		Sav.setDateCreate(this.DateCreate);
		Sav.setFlgActive(this.FlgActive);
		Sav.setNumber(this.Number);
		Sav.setProduct(this.Product);
		Sav.setRate(this.Rate);
		Sav.setType(this.Type);
		
		return Sav;
	}
	
	public List<Accounts_Relationship> GetRelation() {
		
		ArrayList<Accounts_Relationship> ARS = new ArrayList<Accounts_Relationship>();
		
		for (String CodTitT : CodTit) {
			Accounts_Relationship AR = new Accounts_Relationship();
			
			AR.setCodClient(CodTitT);
			AR.setCurrency(this.Currency);
			AR.setNumber(this.Number);
			AR.setProduct(this.Product);
			AR.setRelCod(1);
			
			ARS.add(AR);
		}
		
		for (String CodSigT : CodSig) {
			Accounts_Relationship AR = new Accounts_Relationship();
			
			AR.setCodClient(CodSigT);
			AR.setCurrency(this.Currency);
			AR.setNumber(this.Number);
			AR.setProduct(this.Product);
			AR.setRelCod(2);
			
			ARS.add(AR);
		}
		
		
		return ARS;
	}
	
}
