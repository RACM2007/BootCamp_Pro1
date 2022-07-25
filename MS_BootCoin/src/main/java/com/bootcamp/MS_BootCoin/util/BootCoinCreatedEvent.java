package com.bootcamp.MS_BootCoin.util;

import com.bootcamp.MS_BootCoin.model.BootCoin;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BootCoinCreatedEvent extends Event<BootCoin>{

}
