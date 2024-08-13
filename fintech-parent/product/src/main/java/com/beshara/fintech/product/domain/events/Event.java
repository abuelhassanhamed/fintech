package com.beshara.fintech.product.domain.events;

public class Event<T> {
 private T payload;
 
 public Event() {}

public T getPayload() {
	return payload;
}

public void setPayload(T payload) {
	this.payload = payload;
}
}
