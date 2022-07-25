package com.bootcamp.MS_Transactions.events;


import com.bootcamp.MS_Transactions.Entity.Pay;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PayCreatedEvent extends Event<Pay>{

}
