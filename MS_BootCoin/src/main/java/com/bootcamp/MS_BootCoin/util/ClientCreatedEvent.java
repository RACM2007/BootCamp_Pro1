package com.bootcamp.MS_BootCoin.util;

import com.bootcamp.MS_BootCoin.Entity.MSClientKaf;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientCreatedEvent extends Event<MSClientKaf>{

}
