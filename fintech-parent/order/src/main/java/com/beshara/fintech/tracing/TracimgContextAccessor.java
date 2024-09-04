package com.beshara.fintech.tracing;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.annotation.Nullable;

import io.opentelemetry.context.propagation.TextMapGetter;
import io.opentelemetry.context.propagation.TextMapSetter;

public class TracimgContextAccessor implements TextMapSetter<TracingContextHolder>,TextMapGetter<TracingContextHolder>{

	Map<String, String> context=new HashMap();
	
	@Override
	public void set(@Nullable TracingContextHolder holder, String key, String value) {
		 //Properties props = new Properties();
	     //   props.put(key, value);
	    //    String context = export(props);
	        if (Objects.nonNull(holder)) {
	        
	        	context.put(key, value);
	        	System.out.println("key : "+key +"value :"+value);
	         //  dataMap.put(OutboxConstants.TRACING_SPAN_CONTEXT, context);
	        	holder.setContext(context);
	        }
		
	}
	
	 private static String export(Properties props) {
	        try (Writer sw = new StringWriter()) {
	            props.store(sw, null);
	            return sw.toString();
	        }
	        catch (IOException e) {
	            throw new RuntimeException();
	        }
	    }
	 public static TracimgContextAccessor createSetter() {
	        return new TracimgContextAccessor();
	    }
	 
	 public static TracimgContextAccessor createGetter() {
	        return new TracimgContextAccessor();
	    }

	@Override
	public Iterable<String> keys(TracingContextHolder carrier) {
		// TODO Auto-generated method stub
		return (Iterable<String>) carrier.getContext().keySet().iterator();
	}

	@Override
	@Nullable
	public String get(@Nullable TracingContextHolder carrier, String key) {
		 if (carrier == null) {
		        return null;
		      }
		 Map<String, String> dataMap=carrier.getContext();
		      for (Map.Entry<String, String> entry : dataMap.entrySet()) {
		        if (key.equalsIgnoreCase(entry.getKey())) {
		          return entry.getValue();
		        }
		      }
		      return null;
	}
}
