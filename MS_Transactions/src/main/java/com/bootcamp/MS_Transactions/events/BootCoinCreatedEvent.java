package com.bootcamp.MS_Transactions.events;


import com.bootcamp.MS_Transactions.Entity.BootCoin;
import com.bootcamp.MS_Transactions.Entity.TraBootCoin;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BootCoinCreatedEvent extends Event<TraBootCoin>{

}
