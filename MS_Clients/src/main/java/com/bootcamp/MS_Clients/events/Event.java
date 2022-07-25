package com.bootcamp.MS_Clients.events;

import java.util.Date;

import lombok.Data;

@Data
public class Event <T> {

	private String id;
	private Date date;
	private EventType type;
	private T data;
	
}
