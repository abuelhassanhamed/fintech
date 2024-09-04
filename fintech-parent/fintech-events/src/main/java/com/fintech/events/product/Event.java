package com.fintech.events.product;

public class Event<T> {

	private T payLoad;

	public T getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(T payLoad) {
		this.payLoad = payLoad;
	}
}
