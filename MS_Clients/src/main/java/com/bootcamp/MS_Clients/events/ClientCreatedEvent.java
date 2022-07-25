package com.bootcamp.MS_Clients.events;

import com.bootcamp.MS_Clients.Entity.MSClientKaf;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientCreatedEvent extends Event<MSClientKaf>{

}
