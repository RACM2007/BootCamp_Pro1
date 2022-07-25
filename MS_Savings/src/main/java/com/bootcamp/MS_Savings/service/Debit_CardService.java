package com.bootcamp.MS_Savings.service;

import com.bootcamp.MS_Savings.Entity.SavingObj;

public interface Debit_CardService {

	public SavingObj GetPrinSav (String Number);

	public String GetCodCli(String debitCardNumber);
	
}
