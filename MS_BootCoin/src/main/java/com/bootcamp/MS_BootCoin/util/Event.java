package com.bootcamp.MS_BootCoin.util;

import java.util.Date;

import lombok.Data;
//nunca se utiliza como tal
@Data
public abstract class Event <T> {

	private String id;
	private Date date;
	private EventType type;
	private T data;
	
}
