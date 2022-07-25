package com.MS_Yanki.MS_Yanki.util;

import com.MS_Yanki.MS_Yanki.Entity.MSClientKaf;
import com.MS_Yanki.MS_Yanki.Entity.Pay;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PayCreatedEvent extends Event<Pay>{

}
