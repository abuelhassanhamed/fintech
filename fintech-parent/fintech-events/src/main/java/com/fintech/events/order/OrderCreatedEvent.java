package com.fintech.events.order;

import java.util.Date;

public class OrderCreatedEvent extends OrderEvent {
	
	private Date creationDate=new Date();

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	

}