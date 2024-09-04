package com.beshara.fintech.tracing;

import java.util.HashMap;
import java.util.Map;

public class TracingContextHolder {
	
	private Map context=new HashMap();

	public Map getContext() {
		return context;
	}

	public void setContext(Map context) {
		this.context = context;
	};

	
	

}
